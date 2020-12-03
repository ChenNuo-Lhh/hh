package com.baizhi.hh.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * WebFilter:将某个类修改成配置类
 * 参数:
 * filterName : 配置一个name,防止多个filterName重名
 * urlPatterns :配置需要拦截的路径,  jsp页面路径
 */
@WebFilter(filterName = "MyFilter", urlPatterns = "/main/main.jsp")
public class MyFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        获取当前请求
        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        获取session作用域对象
        Object admin = request.getSession().getAttribute("admin");
//        判断作用域对象状态
//        等于 空  即为未登陆 强制跳转至登陆页面
        if (admin == null) {
            servletRequest.getRequestDispatcher("/login/login.jsp").forward(servletRequest, servletResponse);
        } else {
//                放行
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
