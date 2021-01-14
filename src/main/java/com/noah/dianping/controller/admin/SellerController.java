package com.noah.dianping.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.noah.dianping.common.*;
import com.noah.dianping.model.SellerModel;
import com.noah.dianping.request.PageQuery;
import com.noah.dianping.request.SellerCreateReq;
import com.noah.dianping.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author yanghaiqiang
 * @Date 2021/01/12 15:50
 * @ClassName dianping
 * @Description 商家管理控制器
 **/
@Controller("/admin/seller")
@RequestMapping("/admin/seller")
public class SellerController {

    @Autowired
    private SellerService sellerService;

    /*
    * @Author yanghaiqiang
    * @Description 商户列表
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
        List<SellerModel> sellerModelList =sellerService.selectAll();

        PageInfo<SellerModel> sellerModelPageInfo=new PageInfo<>(sellerModelList);
        ModelAndView modelAndView=new ModelAndView("/admin/seller/index.html");
        modelAndView.addObject("data",sellerModelPageInfo);
        modelAndView.addObject("CONTROLLER_NAME","seller");
        modelAndView.addObject("ACTION_NAME","index");

        return modelAndView;
    }

    /*
    * @Author yanghaiqiang
    * @Description 创建用户
    * @Date 16:10 2021/1/12
    * @Param []
    * @return org.springframework.web.servlet.ModelAndView
    */
    @RequestMapping("/createpage")
    @AdminPermission
    public ModelAndView createPage(){
        ModelAndView modelAndView=new ModelAndView("/admin/seller/create.html");
        modelAndView.addObject("CONTROLLER_NAME","seller");
        modelAndView.addObject("ACTION_NAME","create");
        return modelAndView;
    }

    @RequestMapping(value = "/create",method = RequestMethod.POST)
    @AdminPermission
    public String create(@Valid SellerCreateReq req, BindingResult bindingResult) throws BusinessException {
        if(bindingResult.hasErrors()){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, CommonUtil.processErrorString(bindingResult));
        }
        SellerModel sellerModel=new SellerModel();
        sellerModel.setName(req.getName());
        sellerService.create(sellerModel);

        return "redirect:/admin/seller/index";
    }

    /*
    * @Author yanghaiqiang
    * @Description 禁用商家
    * @Date 17:09 2021/1/12 
    * @Param [id]
    * @return com.noah.dianping.common.CommonRes 
    */
    @RequestMapping(value="down",method = RequestMethod.POST)
    @AdminPermission
    @ResponseBody
    public CommonRes down(@RequestParam(value="id")Integer id) throws BusinessException {
        SellerModel sellerModel = sellerService.changeStatus(id,1);
        return CommonRes.create(sellerModel);
    }

    /*
    * @Author yanghaiqiang
    * @Description 启用商家
    * @Date 17:09 2021/1/12 
    * @Param [id]
    * @return com.noah.dianping.common.CommonRes 
    */
    @RequestMapping(value="up",method = RequestMethod.POST)
    @AdminPermission
    @ResponseBody
    public CommonRes up(@RequestParam(value="id")Integer id) throws BusinessException {
        SellerModel sellerModel = sellerService.changeStatus(id,0);
        return CommonRes.create(sellerModel);
    }

}
