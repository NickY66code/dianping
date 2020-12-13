package com.noah.dianping.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @RequestMapping("/test")
    @ResponseBody
    public String test() {
        return "test";
    }
}
