package com.tensquare.user.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 安全配置类
 * @author zhangbl
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //authorizeRequests 所有security全注解配置实现的开端, 表示开始说明需要的权限
        //需要的权限分两部分, 第一部分是拦截的路径, 第二部分访问该路径需要的权限
        //antMatchers 表示拦截什么路径 permitAll 任何权限都可以, 直接放行所有
        //angRequest() 任何的请求
        //.and().csrf().disable(); 固定写法 表示csrf拦截失效 (CSRF 攻击与防御)
        http
                .authorizeRequests()
                .antMatchers("/**").permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable();
    }
}
