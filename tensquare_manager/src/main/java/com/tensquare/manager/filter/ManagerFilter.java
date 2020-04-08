package com.tensquare.manager.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import util.JwtUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: zbl
 * @Date: 10:43 2019/12/1
 * @Description:
 */
@Component
public class ManagerFilter extends ZuulFilter {

    @Autowired
    private JwtUtil jwtUtil;

    /**
     *
     * @return pre 在操作之前过滤 post 在操作只有过滤
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 多个过滤器的执行顺序
     * @return 排序 0 表示优先执行 可以写多个过滤器 数字越小 优先级越高
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 当前过滤器是否开启, true开启
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 过滤器内执行的操作 return任何object的值 都表示继续执行
     * setSendZullResponse(false) 表示不再继续执行
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        System.out.println("经过manager过滤器");
        //得到request上下文
        RequestContext context = RequestContext.getCurrentContext();
        //得到request域
        HttpServletRequest request = context.getRequest();

        if (request.getMethod().equals("OPTIONS")) {
            return null;
        }

        //登陆请求
        if (request.getRequestURL().indexOf("login") > 0 ) {
            System.out.println("登录页面");
            return null;
        }

        //判断是否有头信息
        String header = request.getHeader("Authorization");
        if (header != null && !"".equals(header)) {
            if (header.startsWith("Bearer ")) {
                String token = header.substring(7);
                //对令牌进行验证
                try {
                    Claims claims = jwtUtil.parseJWT(token);
                    String roles = (String)claims.get("roles");
                    if ("admin".equals(roles)){
                        context.addZuulResponseHeader("Authorization", header);
                        return null;
                    }

                } catch (Exception e){
                    e.printStackTrace();
                    //终止执行
                    context.setSendZuulResponse(false);
                }
            }
        }
        context.setSendZuulResponse(false);
        context.setResponseStatusCode(403);
        context.setResponseBody("权限不足");
        context.getResponse().setContentType("text/html;charset=utf-8");
        return null;
    }
}
