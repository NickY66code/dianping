package com.noah.dianping.recommend;

import org.apache.commons.lang.StringUtils;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.ForeachPartitionFunction;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.ml.recommendation.ALSModel;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.catalyst.expressions.GenericRowWithSchema;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

/**
 * @Author yanghaiqiang
 * @Date 2021/03/26 17:51
 * @ClassName dianping
 * @Description
 **/
public class AlsRecallProduce implements Serializable {

    public static void main(String[] args) {
        //初始化spark运行环境
        SparkSession spark =SparkSession.builder().master("local").appName("DianpingApp").getOrCreate();

        //加载模型进内存
        ALSModel alsModel=ALSModel.load("file:///E:\\BaiduYunDownload\\devtool\\data\\alsmodel");

        JavaRDD<String> csvFile =spark.read().textFile("file:///E:\\BaiduYunDownload\\devtool\\data\\behavior.csv").toJavaRDD();

        JavaRDD<AlsRecallTrain.Rating> ratingJavaRDD=csvFile.map(new Function<String, AlsRecallTrain.Rating>() {
            @Override
            public AlsRecallTrain.Rating call(String v1) throws Exception {
                return AlsRecallTrain.Rating.parseRating(v1);
            }
        });
        Dataset<Row> rating=spark.createDataFrame(ratingJavaRDD, AlsRecallTrain.Rating.class);

        //给5个用户做离线的召回结果预测
        Dataset<Row> users=rating.select(alsModel.getUserCol()).distinct().limit(5);
        Dataset<Row> userRecs=alsModel.recommendForUserSubset(users,20);

        userRecs.foreachPartition(new ForeachPartitionFunction<Row>() {
            @Override
            public void call(Iterator<Row> iterator) throws Exception {

                //新建数据库链接
                Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/dianping?user=root&password=root&useUnicode=true&characterEncoding=UTF-8");
                PreparedStatement preparedStatement=connection.prepareCall("insert into recommend(id,recommend) value (?,?)");

                List<Map<String,Object>> data=new ArrayList<>();

                iterator.forEachRemaining(action->{
                    int userId=action.getInt(0);
                    List<GenericRowWithSchema> recommendationList =action.getList(1);
                    List<Integer> shopList=new ArrayList<Integer>();
                    recommendationList.forEach(row->{
                        Integer shopId=row.getInt(0);
                        shopList.add(shopId);
                    });
                    String recommendData = StringUtils.join(shopList,",");
                    Map<String,Object> map=new HashMap<>();
                    map.put("userId",userId);
                    map.put("recommend",recommendData);
                    data.add(map);
                });

                data.forEach(stringObjectMap -> {
                    try {
                        preparedStatement.setInt(1, (Integer) stringObjectMap.get("userId"));
                        preparedStatement.setString(2, (String) stringObjectMap.get("recommend"));

                        preparedStatement.addBatch();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                });
                preparedStatement.executeBatch();
                connection.close();
            }
        });

    }

    public static class Rating implements Serializable{
        private int userId;
        private int shopId;
        private int rating;

        public static AlsRecallTrain.Rating parseRating(String str){
            str =str.replace("\"","");
            String[] strArr=str.split(",");
            int userId= Integer.parseInt(strArr[0]);
            int shopId=Integer.parseInt(strArr[1]);
            int rating=Integer.parseInt(strArr[2]);

            return new AlsRecallTrain.Rating(userId,shopId,rating);
        }

        public Rating(int userId, int shopId, int rating) {
            this.userId = userId;
            this.shopId = shopId;
            this.rating = rating;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getShopId() {
            return shopId;
        }

        public void setShopId(int shopId) {
            this.shopId = shopId;
        }

        public int getRating() {
            return rating;
        }

        public void setRating(int rating) {
            this.rating = rating;
        }
    }
}
