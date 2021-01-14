package com.noah.dianping.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.noah.dianping.common.AdminPermission;
import com.noah.dianping.common.BusinessException;
import com.noah.dianping.common.CommonUtil;
import com.noah.dianping.common.EmBusinessError;
import com.noah.dianping.model.CategoryModel;
import com.noah.dianping.model.ShopModel;
import com.noah.dianping.request.CategoryCreateReq;
import com.noah.dianping.request.PageQuery;
import com.noah.dianping.request.ShopCreateReq;
import com.noah.dianping.service.ShopService;
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
 * @Date 2021/01/14 17:13
 * @ClassName dianping
 * @Description
 **/
@Controller("/admin/shop")
@RequestMapping("/admin/shop")
public class ShopController {

    @Autowired
    private ShopService shopService;

    /*
     * @Author yanghaiqiang
     * @Description 门店列表
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
        List<ShopModel> shopModelList =shopService.selectAll();

        PageInfo<ShopModel> shopModelPageInfo=new PageInfo<>(shopModelList);
        ModelAndView modelAndView=new ModelAndView("/admin/shop/index.html");
        modelAndView.addObject("data",shopModelPageInfo);
        modelAndView.addObject("CONTROLLER_NAME","shop");
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
        ModelAndView modelAndView=new ModelAndView("/admin/shop/create.html");
        modelAndView.addObject("CONTROLLER_NAME","shop");
        modelAndView.addObject("ACTION_NAME","create");
        return modelAndView;
    }

    @RequestMapping(value = "/create",method = RequestMethod.POST)
    @AdminPermission
    public String create(@Valid ShopCreateReq req, BindingResult bindingResult) throws BusinessException {
        if(bindingResult.hasErrors()){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, CommonUtil.processErrorString(bindingResult));
        }
        ShopModel shopModel=new ShopModel();
        shopModel.setIconUrl(req.getIconUrl());
        shopModel.setAddress(req.getAddress());
        shopModel.setCategoryId(req.getCategoryId());
        shopModel.setEndTime(req.getEndTime());
        shopModel.setStartTime(req.getStartTime());
        shopModel.setLatitude(req.getLatitude());
        shopModel.setLongitude(req.getLongitude());
        shopModel.setName(req.getName());
        shopModel.setPricePerMan(req.getPricePerMan());
        shopModel.setSellerId(req.getSellerId());

        shopService.create(shopModel);
        return "redirect:/admin/shop/index";
    }
}
