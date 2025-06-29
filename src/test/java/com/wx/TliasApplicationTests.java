package com.wx;

import com.wx.Mapper.DeptMapper;
import com.wx.Pojo.Dept;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@SpringBootTest
class TliasApplicationTests {

    //    @Autowired
    private DeptMapper deptMapper;

    @Test
    public void testSelect() {
        List<Dept> depts = deptMapper.select();
        System.out.println(depts);
    }

    @Test
    public void TestGenJWT() {

        Map<String, Object> claims = new HashMap<>();
        claims.put("id", 1);
        claims.put("name", "mrl");
        String s = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, "wxxx")//签名算法
                .addClaims(claims)//数据载荷部分
                .setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000))//添加有效时间
                .compact();

        System.out.println(s);
    }

    @Test
    public void TestParseJWT() {

        Claims body = Jwts.parser().setSigningKey("itheima")
                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoibXJsIiwiaWQiOjEsImV4cCI6MTc1MDQ5ODgyN30.YmRt2dznDUOGLP5fmP82wgmdSpaXlpcgieWUnrGv9ig").getBody();
        System.out.println(body);
    }
}
