package com.noah.dianping.controller;

import com.noah.dianping.common.*;
import com.noah.dianping.model.UserModel;
import com.noah.dianping.request.LoginReq;
import com.noah.dianping.request.RegisterReq;
import com.noah.dianping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * @Author yanghaiqiang
 * @Date 2020/12/13 22:29
 * @ClassName dianping
 * @Description 用户控制层
 **/
@Controller("/user")
@RequestMapping("/user")
public class UserController {

    public static final String CURRENT_USER_SESSION="currentUserSession";

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Autowired
    private UserService userService;

    /*
    * @Author yanghaiqiang
    * @Description 测试
    * @Date 21:34 2020/12/17 
    * @Param 
    * @return  
    */
    @RequestMapping("/test")
    @ResponseBody
    public String test() {
        return "test";
    }

    /*
    * @Author yanghaiqiang
    * @Description 首页
    * @Date 21:34 2020/12/17 
    * @Param []
    * @return org.springframework.web.servlet.ModelAndView 
    */
    @RequestMapping("/index")
    public ModelAndView index(){
        String userName="noah";
        ModelAndView modelAndView=new ModelAndView("/index.html");
        modelAndView.addObject("name",userName);

        return modelAndView;
    }

    /*
    * @Author yanghaiqiang
    * @Description 获取用户信息
    * @Date 21:35 2020/12/17 
    * @Param [id]
    * @return com.noah.dianping.common.CommonRes 
    */
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

    /*
    * @Author yanghaiqiang
    * @Description 用户注册
    * @Date 22:23 2020/12/17
    * @Param [registerReq, bindingResult]
    * @return com.noah.dianping.common.CommonRes
    */
    @RequestMapping("/register")
    @ResponseBody
    public CommonRes register(@Valid @RequestBody RegisterReq registerReq, BindingResult bindingResult) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {
        if(bindingResult.hasErrors()){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, CommonUtil.processErrorString(bindingResult));
        }
        UserModel registerUser=new UserModel();
        registerUser.setTelphone(registerReq.getTelphone());
        registerUser.setPassword(registerReq.getPassword());
        registerUser.setNickName(registerReq.getNickName());
        registerUser.setGender(registerReq.getGender());

        UserModel resUserModel =userService.register(registerUser);
        return  CommonRes.create(resUserModel);
    }

    @RequestMapping("/login")
    @ResponseBody
    public CommonRes login(@Valid @RequestBody LoginReq loginReq,BindingResult bindingResult) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {
        if(bindingResult.hasErrors()){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, CommonUtil.processErrorString(bindingResult));
        }
        UserModel userModel=userService.login(loginReq.getTelphone(),loginReq.getPassword());
        httpServletRequest.getSession().setAttribute(CURRENT_USER_SESSION,userModel);
        return CommonRes.create(userModel);
    }

    /*
    * @Author yanghaiqiang
    * @Description 注销用户
    * @Date 22:46 2020/12/17
    * @Param []
    * @return com.noah.dianping.common.CommonRes
    */
    @RequestMapping("/logout")
    @ResponseBody
    public CommonRes login(){
        httpServletRequest.getSession().invalidate();
        return CommonRes.create(null);
    }

    /*
    * @Author yanghaiqiang
    * @Description 获取当前用户信息
    * @Date 22:48 2020/12/17 
    * @Param []
    * @return com.noah.dianping.common.CommonRes 
    */
    @RequestMapping("/getCurrentUser")
    @ResponseBody
    public CommonRes getCurrentUser(){
        UserModel userModel=(UserModel) httpServletRequest.getSession().getAttribute(CURRENT_USER_SESSION);
        
        return CommonRes.create(userModel);
    }
}
