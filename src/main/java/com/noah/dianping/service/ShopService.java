package com.noah.dianping.service;

import com.noah.dianping.common.BusinessException;
import com.noah.dianping.model.ShopModel;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface ShopService {

    /*
    * @Author yanghaiqiang
    * @Description 创建
    * @Date 16:58 2021/1/14
    * @Param [shopModel]
    * @return com.noah.dianping.model.ShopModel
    */
    ShopModel create(ShopModel shopModel) throws BusinessException;

    /*
    * @Author yanghaiqiang
    * @Description 获取
    * @Date 16:58 2021/1/14
    * @Param [id]
    * @return com.noah.dianping.model.ShopModel
    */
    ShopModel get(Integer id);

    /*
    * @Author yanghaiqiang
    * @Description 获取所有
    * @Date 16:58 2021/1/14
    * @Param []
    * @return java.util.List<com.noah.dianping.model.ShopModel>
    */
    List<ShopModel> selectAll();

    /*
    * @Author yanghaiqiang
    * @Description 统计数量
    * @Date 17:55 2021/1/14
    * @Param []
    * @return java.lang.Integer
    */
    Integer countAllShop();
    
    /*
    * @Author yanghaiqiang
    * @Description 默认排序
    * @Date 23:45 2021/1/16
    * @Param [longitude, latitude]
    * @return java.util.List<com.noah.dianping.model.ShopModel> 
    */
    List<ShopModel> recommend(BigDecimal longitude,BigDecimal latitude);

    /*
    * @Author yanghaiqiang
    * @Description 搜索
    * @Date 16:24 2021/1/18
    * @Param [longitude, latitude, keyword]
    * @return java.util.List<com.noah.dianping.model.ShopModel>
    */
    List<ShopModel> search(BigDecimal longitude,BigDecimal latitude,String keyword,Integer orderby ,Integer categoryId,String tags);

    /*
    * @Author yanghaiqiang
    * @Description 查询标签
    * @Date 17:19 2021/1/18
    * @Param [keyword, categoryId, tags]
    * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
    */
    List<Map<String,Object>> searchGroupByTags(String keyword,Integer categoryId,String tags);

    Map<String,Object> searchES(BigDecimal longitude,BigDecimal latitude,String keyword,Integer orderby ,Integer categoryId,String tags) throws IOException;
}
