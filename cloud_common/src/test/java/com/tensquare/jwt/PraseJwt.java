package com.tensquare.jwt;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import io.jsonwebtoken.*;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @Author: zbl
 * @Date: 11:17 2019/10/5
 * @Description: 解析
 */
public class PraseJwt {
    public static void main(String[] args) {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI2NjYiLCJzdWIiOiLnlKjmiLflkI0iLCJpYXQiOjE1NzAyNDY3OTIsImV4cCI6MTU3MDI0Njg1Miwicm9sZSI6ImFkbWluIiwicm9vbSI6IjgwNiJ9.sJrysvLnFOaZtUPGwnpTvN0crCwKHUoFIGiC18ZC5wA";
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey("huatu")
                    .parseClaimsJws(token)
                    .getBody();
            System.out.println("id : " + claims.getId());
            System.out.println("用户名 : " + claims.getSubject());
            System.out.println("time : " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(claims.getIssuedAt()));
            System.out.println("过期time : " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(claims.getExpiration()));
            System.out.println("now" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            System.out.println("role : " + claims.get("role"));
            System.out.println("room : " + claims.get("room"));
        } catch (Exception e) {
            System.out.println("token错误/过期");
        }



    }
}
