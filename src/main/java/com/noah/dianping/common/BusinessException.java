package com.noah.dianping.common;

/**
 * @Author yanghaiqiang
 * @Date 2020/12/16 22:38
 * @ClassName dianping
 * @Description 错误异常
 **/
public class BusinessException extends Exception {
    private CommonError commonError;

    public BusinessException(EmBusinessError emBusinessError){
        super();
        this.commonError=new CommonError(emBusinessError);
    }

    public CommonError getCommonError() {
        return commonError;
    }

    public void setCommonError(CommonError commonError) {
        this.commonError = commonError;
    }
}
