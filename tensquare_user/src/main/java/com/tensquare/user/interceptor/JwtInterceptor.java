package com.tensquare.user.interceptor;

import io.jsonwebtoken.Claims;
import org.omg.PortableInterceptor.Interceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import util.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.handler.Handler;

/**
 * @Author: zbl
 * @Date: 13:01 2019/10/5
 * @Description:
 */
@Component
public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("经过了拦截器");
        //无论如何都放行, 具体能不能操作在具体业务中操作
        //拦截器只是负责吧请求头中包含的token进行解析
        String header = request.getHeader("Authorization");
        if (header != null && !"".equals(header)){
            //如果有头Authorization 就对其解析
            if (header.startsWith("Bearer ")){
                //获取token
                String token = header.substring(7);
                //对令牌进行验证
                try {
                    Claims claims = jwtUtil.parseJWT(token);
                    String roles = (String)claims.get("roles");
                    if ("admin".equals(roles)){
                        request.setAttribute("claims_admin", claims);
                    }
                    if ("user".equals(roles)){
                        request.setAttribute("claims_user", claims);
                    }
                } catch (Exception e){
                    throw new RuntimeException("token有误 !");
                }
            }
        }
        return true;
    }
}
