package com.baizhi.hh.Interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

public class MyInterceptor implements HandlerInterceptor {
   /* @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("进入拦截器");
        Object admin = request.getSession().getAttribute("admin");
        if(admin == null){
            System.out.println("跳转");
            response.sendRedirect(request.getContextPath()+"/login/login.jsp");
            return false;
        }
        return true;
    }*/
}
