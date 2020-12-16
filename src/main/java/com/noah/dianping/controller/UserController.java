package com.noah.dianping.controller;

import com.noah.dianping.common.BusinessException;
import com.noah.dianping.common.CommonError;
import com.noah.dianping.common.CommonRes;
import com.noah.dianping.common.EmBusinessError;
import com.noah.dianping.model.UserModel;
import com.noah.dianping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author yanghaiqiang
 * @Date 2020/12/13 22:29
 * @ClassName dianping
 * @Description 用户控制层
 **/
@Controller("/user")
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/test")
    @ResponseBody
    public String test() {
        return "test";
    }

    @RequestMapping("/index")
    public ModelAndView index(){
        String userName="noah";
        ModelAndView modelAndView=new ModelAndView("/index.html");
        modelAndView.addObject("name",userName);

        return modelAndView;
    }

    @RequestMapping("/get")
    @ResponseBody
    public CommonRes getUser(@RequestParam(name = "id")Integer id) throws BusinessException {
        UserModel userModel=userService.getUser(id);
        if(null ==userModel){
            //return CommonRes.create(new CommonError(EmBusinessError.NO_OBJECT_FOUND),"fail");
            throw new BusinessException(EmBusinessError.NO_OBJECT_FOUND);
        }
        return CommonRes.create(userModel);
    }
}
