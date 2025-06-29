package com.wx.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

public class jwtUtils {

    private static String signKey = "wxxx";
    private static Long expire = 4320000L;

    /*
     * 生成jwt令牌*/

    public static String generateJWT(Map<String, Object> claims) {
        String jwt = Jwts.builder().addClaims(claims)//数据载荷部分
                .signWith(SignatureAlgorithm.HS256, signKey)//加密类型和签名密钥
                .setExpiration(new Date(System.currentTimeMillis() + expire))//设置过期时间
                .compact();

        return jwt;
    }


    /*
     * 解析JWT令牌*/

    public static Claims parseJWT(String jwt) {
        Claims body = Jwts.parser().setSigningKey(signKey).parseClaimsJws(jwt).getBody();

        return body;
    }
}
