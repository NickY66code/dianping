package com.noah.dianping.service;

import com.noah.dianping.common.BusinessException;
import com.noah.dianping.model.CategoryModel;

import java.util.List;

public interface CategoryService {

    /*
    * @Author yanghaiqiang
    * @Description 创建
    * @Date 15:32 2021/1/14
    * @Param [categoryModel]
    * @return com.noah.dianping.model.CategoryModel
    */
    CategoryModel create(CategoryModel categoryModel) throws BusinessException;

    /*
    * @Author yanghaiqiang
    * @Description 获取
    * @Date 15:32 2021/1/14
    * @Param [id]
    * @return com.noah.dianping.model.CategoryModel
    */
    CategoryModel get(Integer id);

    /*
    * @Author yanghaiqiang
    * @Description 查询整个列表
    * @Date 15:32 2021/1/14
    * @Param []
    * @return java.util.List<com.noah.dianping.model.CategoryModel>
    */
    List<CategoryModel> selectAll();

    /*
     * @Author yanghaiqiang
     * @Description 统计数量
     * @Date 17:55 2021/1/14
     * @Param []
     * @return java.lang.Integer
     */
    Integer countAllCategory();
}
