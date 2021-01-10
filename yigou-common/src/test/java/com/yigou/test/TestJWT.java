/**
 * -*- encoding: utf-8 -*-
 *
 * @Time : 2021/1/9 15:03
 * @Author : mike.liu
 * @File : TestJWT.java
 **/

package com.yigou.test;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.Test;

import java.util.Date;

public class TestJWT {
    /***
     * 创建Jwt令牌
     */
    @Test
    public void testCreateJwt(){
        JwtBuilder builder = Jwts.builder()
                .setId("8888")      //设置唯一编号
                .setSubject("测试")   // 设置主题，可以是JSON数据
                .setIssuedAt(new Date())    //设置签发日期
                .signWith(SignatureAlgorithm.HS256, "itpertest");   //设置前面，使用Hs256算法，并设置SecretKey(字符串)
            //构建并返回一个字符串
            System.out.println(builder.compact());
    }
}
