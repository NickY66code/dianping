package com.noah.dianping.common;

import com.noah.dianping.controller.admin.AdminController;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @Author yanghaiqiang
 * @Date 2020/12/27 21:42
 * @ClassName dianping
 * @Description 控制层AOP
 **/
@Aspect
@Configuration
public class ControllerAspert {

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Autowired
    private HttpServletResponse httpServletResponse;

    //只对controller.admin下的所有类下的打有RequestMapping标签的类生效
    @Around("execution(* com.noah.dianping.controller.admin.*.*(..)) && @annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public Object adminControllerBeforeValidation(ProceedingJoinPoint joinPoint) throws Throwable {
        Method method=((MethodSignature)joinPoint.getSignature()).getMethod();
        AdminPermission adminPermission=method.getAnnotation(AdminPermission.class);
        if(adminPermission==null){
            //公共方法
            Object resultObject =joinPoint.proceed();
            return  resultObject;
        }
        //判断当前管理员是否登录
        String email=(String)httpServletRequest.getSession().getAttribute(AdminController.CURRENT_ADMIN_SESSION);
        if (email==null){
            //未登录重定向到登陆界面
            if (adminPermission.produceType().equals("text/html")){
                httpServletResponse.sendRedirect("/admin/admin/loginpage");
                return null;
            }else {
             CommonError commonError=new CommonError(EmBusinessError.ADMIN_SHOULD_LOGIN);
            return CommonRes.create(commonError,"fail");
            }
        }else {
            //放行
            Object resultObject =joinPoint.proceed();
            return resultObject;
        }
    }
}
