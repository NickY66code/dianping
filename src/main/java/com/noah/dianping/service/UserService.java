package com.noah.dianping.service;

import com.noah.dianping.common.BusinessException;
import com.noah.dianping.model.UserModel;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;


public interface UserService {

    /*
    * @Author yanghaiqiang
    * @Description 获取用户信息
    * @Date 23:07 2020/12/14
    * @Param [id]
    * @return com.noah.dianping.model.UserModel
    */
    UserModel getUser(Integer id);
    
    /*
    * @Author yanghaiqiang
    * @Description 用户注册
    * @Date 22:09 2020/12/17
    * @Param [registerUser]
    * @return com.noah.dianping.model.UserModel 
    */
    UserModel register(UserModel registerUser) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException;

    /*
    * @Author yanghaiqiang
    * @Description 用户登录
    * @Date 22:17 2020/12/17
    * @Param [telphone, password]
    * @return com.noah.dianping.model.UserModel
    */
    UserModel login(String telphone,String password) throws BusinessException;
}
