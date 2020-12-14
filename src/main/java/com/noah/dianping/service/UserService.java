package com.noah.dianping.service;

import com.noah.dianping.model.UserModel;


public interface UserService {

    /*
    * @Author yanghaiqiang
    * @Description 获取用户信息
    * @Date 23:07 2020/12/14
    * @Param [id]
    * @return com.noah.dianping.model.UserModel
    */
    UserModel getUser(Integer id);
}
