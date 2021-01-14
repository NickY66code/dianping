package com.noah.dianping.service;

import com.noah.dianping.common.BusinessException;
import com.noah.dianping.model.SellerModel;

import java.util.List;

public interface SellerService {
    
    /*
    * @Author yanghaiqiang
    * @Description 创建商户
    * @Date 15:49 2021/1/12 
    * @Param [sellerModel]
    * @return com.noah.dianping.model.SellerModel 
    */
    SellerModel create(SellerModel sellerModel);
    
    /*
    * @Author yanghaiqiang
    * @Description 查询商户
    * @Date 15:49 2021/1/12 
    * @Param [id]
    * @return com.noah.dianping.model.SellerModel 
    */
    SellerModel get(Integer id);
    
    /*
    * @Author yanghaiqiang
    * @Description 查询所有用户
    * @Date 15:49 2021/1/12 
    * @Param []
    * @return java.util.List<com.noah.dianping.model.SellerModel> 
    */
    List<SellerModel> selectAll();
    
    /*
    * @Author yanghaiqiang
    * @Description 修改状态
    * @Date 16:26 2021/1/12
    * @Param [id, disabledFlag]
    * @return com.noah.dianping.model.SellerModel 
    */
    SellerModel changeStatus(Integer id,Integer disabledFlag) throws BusinessException;

    /*
     * @Author yanghaiqiang
     * @Description 统计数量
     * @Date 17:55 2021/1/14
     * @Param []
     * @return java.lang.Integer
     */
    Integer countAllSeller();
}
