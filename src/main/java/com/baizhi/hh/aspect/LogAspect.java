package com.baizhi.hh.aspect;

import com.baizhi.hh.annotcation.AddLog;
import com.baizhi.hh.entity.Admin;
import com.baizhi.hh.entity.Log;
import com.baizhi.hh.service.LogService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.Date;


@Configuration
@Aspect
public class LogAspect {
    //日志
    @Resource
    HttpSession session;
    @Autowired
    private LogService logService;

    /**
     * 谁   时间   干了什么   是否成功
     */
    @Around("@annotation(com.baizhi.hh.annotcation.AddLog)")
    public Object addLog(ProceedingJoinPoint proceedingJoinPoint) {
//        获取用户数据
        Admin admin = (Admin) session.getAttribute("admin");
//        获取方法名
        String methodName = proceedingJoinPoint.getSignature().getName();
//        System.out.println("methodName:"+methodName);
//        methodName:addUser
//        获取方法
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        Method method = signature.getMethod();
//        获取注解
        AddLog addLog = method.getAnnotation(AddLog.class);
//        获取注解对应属性值
        String value = addLog.value();
//        方法放行
        Object result = null;
        String message = null;
        try {
            result = proceedingJoinPoint.proceed();
            message = "success";
        } catch (Throwable throwable) {
            message = "error";
        }
//        数据入库
        Log log = new Log(null, admin.getUsername(), new Date(), value + "[" + methodName + "]", message);
        logService.addLog(log);
        System.out.println("log:" + log);
        return result;
    }

}
