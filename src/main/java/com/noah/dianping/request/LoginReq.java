package com.noah.dianping.request;

import javax.validation.constraints.NotBlank;

/**
 * @Author yanghaiqiang
 * @Date 2020/12/17 22:24
 * @ClassName dianping
 * @Description 登录入参
 **/
public class LoginReq {
    @NotBlank(message = "手机号码不能为空")
    private String telphone;

    @NotBlank(message = "密码不能为空")
    private String password;

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
