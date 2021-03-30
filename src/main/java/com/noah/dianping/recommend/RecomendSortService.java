package com.noah.dianping.recommend;

import org.apache.spark.ml.classification.LogisticRegressionModel;

import org.apache.spark.ml.linalg.Vector;
import org.apache.spark.ml.linalg.Vectors;
import org.apache.spark.sql.SparkSession;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author yanghaiqiang
 * @Date 2021/03/30 16:15
 * @ClassName dianping
 * @Description
 **/
@Service
public class RecomendSortService {

    private SparkSession spark;

    private LogisticRegressionModel lrModel;

    @PostConstruct
    public void init(){
        //加载LR模型
        spark =SparkSession.builder().master("local").appName("DianpingApp").getOrCreate();

        lrModel =LogisticRegressionModel.load("file:///E:\\BaiduYunDownload\\devtool\\data\\lrmodel");
    }

    public List<Integer> sort(List<Integer> shopIdList,Integer usreId){
        //需要根据lrmodel所需要11维的x，生成特征，然后调用其预测方法
        List<ShopSortModel> list=new ArrayList<>();
        for(Integer shopId : shopIdList){
            //造的假数据，可以从数据库或者缓存中拿到对应的性别年龄评分价格等特征数据转化城feture向量
            Vector v= Vectors.dense(1,0,0,0,0,1,0.6,0,0,1,0);
            Vector result=lrModel.predictProbability(v);
            double[] arr =result.toArray();
            double score=arr[1];
            ShopSortModel shopSortModel=new ShopSortModel();
            shopSortModel.setShopId(shopId);
            shopSortModel.setScore(score);
            list.add(shopSortModel);
        }
        list.sort(new Comparator<ShopSortModel>(){
            @Override
            public int compare(ShopSortModel o1, ShopSortModel o2) {
                if (o1.getScore()<o2.getScore()){
                    return 1;
                }else if (o1.getScore()>o2.getScore()){
                    return -1;
                }else {
                    return 0;
                }
            }
        });
        return list.stream().map(shopSortModel -> shopSortModel.getShopId()).collect(Collectors.toList());
    }
}
