package com.noah.dianping.common;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

/**
 * @Author yanghaiqiang
 * @Date 2020/12/17 22:03
 * @ClassName dianping
 * @Description 通用方法
 **/
public class CommonUtil {

    public static String processErrorString(BindingResult bindingResult){
        if(!bindingResult.hasErrors()){
            return "";
        }
        StringBuilder stringBuilder=new StringBuilder();
        //循环出NotBlank或者null的错误信息
        for(FieldError fieldError:bindingResult.getFieldErrors()){
            stringBuilder.append(fieldError.getDefaultMessage()+",");
        }
        return stringBuilder.substring(0,stringBuilder.length()-1);
    }
}
