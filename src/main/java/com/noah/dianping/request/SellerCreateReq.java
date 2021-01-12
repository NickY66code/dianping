package com.noah.dianping.request;

import javax.validation.constraints.NotBlank;

/**
 * @Author yanghaiqiang
 * @Date 2021/01/12 16:14
 * @ClassName dianping
 * @Description 创建商家
 **/
public class SellerCreateReq {

    @NotBlank(message = "商户名不能为空")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
