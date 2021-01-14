package com.noah.dianping.controller;

import com.noah.dianping.common.CommonRes;
import com.noah.dianping.model.CategoryModel;
import com.noah.dianping.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author yanghaiqiang
 * @Date 2021/01/14 16:12
 * @ClassName dianping
 * @Description
 **/
@Controller("/category")
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @ResponseBody
    @RequestMapping("/list")
    public CommonRes list(){
        List<CategoryModel> categoryModelList=categoryService.selectAll();
        return CommonRes.create(categoryModelList);
    }
}
