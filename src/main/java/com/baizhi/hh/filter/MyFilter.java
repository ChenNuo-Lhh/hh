package com.baizhi.hh.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter
public class MyFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        获取当前请求
        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        输出当前路径
        String url = request.getRequestURI();
        Object admin = request.getSession().getAttribute("admin");
        if (admin == null) {
            if (url.equals("/yingx/main/main.jsp")) {
                System.out.println("拦截成功");
                servletRequest.getRequestDispatcher("/login/login.jsp").forward(servletRequest, servletResponse);
            }
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }


    }
}
