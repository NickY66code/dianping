package com.noah.dianping.controller;

import com.noah.dianping.model.UserModel;
import com.noah.dianping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @RequestMapping("/get")
    @ResponseBody
    public UserModel getUser(@RequestParam(name = "id")Integer id){
        return userService.getUser(id);
    }
}
