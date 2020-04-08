package com.tensquare.friend.config;

import com.tensquare.friend.interceptor.JwtInterceptor;
import com.tensquare.friend.interceptor.JwtInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @Author: zbl
 * @Date: 13:09 2019/10/5
 * @Description:
 */
@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class InterceptorConfig extends WebMvcConfigurationSupport {

    @Autowired
    private JwtInterceptor jwtInterceptor;

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        //注册拦截器, 要声明拦截器对象, 和要拦截的请求
        //excludePathPatterns 排除
        registry.addInterceptor(jwtInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/**/login/**");
    }
}
