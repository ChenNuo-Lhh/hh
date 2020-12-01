package com.baizhi.hh.cofing;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class LoginConfig implements WebMvcConfigurer {

    /**
     * 配置拦截器
     * @param registry
     */
    /* @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        拦截那些请求
        registry.addInterceptor(new MyInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/admin/**");
    }*/
}
