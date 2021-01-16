package com.noah.dianping.common;

import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author yanghaiqiang
 * @Date 2020/12/16 22:43
 * @ClassName dianping
 * @Description 切面异常处理
 **/
@ControllerAdvice
public class GlobalExceptionHandler {
//
//    //处理控制层抛出的异常
//    @ExceptionHandler(Exception.class)
//    @ResponseBody
//    public CommonRes doError(HttpServletRequest servletRequest, HttpServletResponse servletResponse,Exception ex){
//        if(ex instanceof BusinessException){
//            return CommonRes.create(((BusinessException)ex).getCommonError(),"fail");
//        }else if(ex instanceof NoHandlerFoundException){
//            //接口404异常
//            CommonError commonError=new CommonError(EmBusinessError.NO_HANDLER_FOUND);
//            return CommonRes.create(commonError,"fail");
//        }else if(ex instanceof ServletRequestBindingException){
//            //执行参数错误
//            CommonError commonError=new CommonError(EmBusinessError.BIND_EXCEPTION_ERROR);
//            return CommonRes.create(commonError,"fail");
//        }
//        else{
//            CommonError commonError=new CommonError(EmBusinessError.UNKNOWN_ERROR);
//            return CommonRes.create(commonError,"fail");
//        }
//    }
}
