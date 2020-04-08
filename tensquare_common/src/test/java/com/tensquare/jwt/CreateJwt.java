package com.tensquare.jwt;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * @Author: zbl
 * @Date: 11:10 2019/10/5
 * @Description:
 */
public class CreateJwt {
    public static void main(String[] args) {
        //Jwts.parser() 验证
        //Jwts.builder() 生成
        //ID 登录用户的id
        //登录用户名  .  登录时间  用什么签名,  盐
        //.setExpiration() 设置过期时间 claim() 自定义参数
        JwtBuilder jwtBuilder = Jwts.builder()
                .setId("666")
                .setSubject("用户名")
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, "huatu")
                .setExpiration(new Date(new Date().getTime() + 60 * 1000))
                .claim("role", "admin")
                .claim("room", "806");
        System.out.println(jwtBuilder.compact());
    }
}
