package com.noah.dianping.controller;

import com.noah.dianping.common.BusinessException;
import com.noah.dianping.common.CommonRes;
import com.noah.dianping.common.EmBusinessError;
import com.noah.dianping.model.ShopModel;
import com.noah.dianping.service.CategoryService;
import com.noah.dianping.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author yanghaiqiang
 * @Date 2021/01/16 23:33
 * @ClassName dianping
 * @Description 用户商品控制器
 **/
@Controller("/shop")
@RequestMapping("/shop")
public class ShopController {

    @Autowired
    private ShopService shopService;

    @Autowired
    private CategoryService categoryService;

    //推荐服务v1.0
    @RequestMapping("/recommend")
    @ResponseBody
    public CommonRes recommend(@RequestParam(name="longitude")BigDecimal longitude,
                               @RequestParam(name = "latitude")BigDecimal latitude) throws BusinessException {
        if(longitude == null || latitude ==null){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
        List<ShopModel> shopModelList=shopService.recommend(longitude,latitude);

        return CommonRes.create(shopModelList);
    }
}
