package com.noah.dianping.service;

import com.noah.dianping.common.BusinessException;
import com.noah.dianping.model.ShopModel;

import java.util.List;

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
}
