package com.noah.dianping.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.noah.dianping.common.AdminPermission;
import com.noah.dianping.common.BusinessException;
import com.noah.dianping.common.CommonUtil;
import com.noah.dianping.common.EmBusinessError;
import com.noah.dianping.model.CategoryModel;
import com.noah.dianping.model.SellerModel;
import com.noah.dianping.request.CategoryCreateReq;
import com.noah.dianping.request.PageQuery;
import com.noah.dianping.request.SellerCreateReq;
import com.noah.dianping.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author yanghaiqiang
 * @Date 2021/01/14 15:42
 * @ClassName dianping
 * @Description
 **/
@Controller("/admin/category")
@RequestMapping("/admin/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /*
     * @Author yanghaiqiang
     * @Description 品类列表
     * @Date 15:57 2021/1/12
     * @Param []
     * @return org.springframework.web.servlet.ModelAndView
     */
    @RequestMapping("/index")
    @AdminPermission
    public ModelAndView index(PageQuery pageQuery){

        //启动分页
        PageHelper.startPage(pageQuery.getPage(),pageQuery.getSize());

        //查询
        List<CategoryModel> categoryModelList =categoryService.selectAll();

        PageInfo<CategoryModel> categoryModelPageInfo=new PageInfo<>(categoryModelList);
        ModelAndView modelAndView=new ModelAndView("/admin/category/index.html");
        modelAndView.addObject("data",categoryModelPageInfo);
        modelAndView.addObject("CONTROLLER_NAME","category");
        modelAndView.addObject("ACTION_NAME","index");

        return modelAndView;
    }

    /*
     * @Author yanghaiqiang
     * @Description 创建商品
     * @Date 16:10 2021/1/12
     * @Param []
     * @return org.springframework.web.servlet.ModelAndView
     */
    @RequestMapping("/createpage")
    @AdminPermission
    public ModelAndView createPage(){
        ModelAndView modelAndView=new ModelAndView("/admin/category/create.html");
        modelAndView.addObject("CONTROLLER_NAME","category");
        modelAndView.addObject("ACTION_NAME","create");
        return modelAndView;
    }

    @RequestMapping(value = "/create",method = RequestMethod.POST)
    @AdminPermission
    public String create(@Valid CategoryCreateReq req, BindingResult bindingResult) throws BusinessException {
        if(bindingResult.hasErrors()){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, CommonUtil.processErrorString(bindingResult));
        }
        CategoryModel categoryModel =new CategoryModel();
        categoryModel.setName(req.getName());
        categoryModel.setIconUrl(req.getIconUrl());
        categoryModel.setSort(req.getSort());
        categoryService.create(categoryModel);

        return "redirect:/admin/category/index";
    }
}
