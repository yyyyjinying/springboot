package com.cloud;

import entity.BCrypt;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.Base64Codec;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

@RunWith(SpringRunner.class)
@SpringBootTest//(classes = SoauthApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class Test01 {

    @Test
    public void getPW(){
        String hashpw = BCrypt.hashpw("112233", BCrypt.gensalt());
        System.out.println(hashpw);
    }

    /**
     * 创建token
     */
    @Test
    public void testCreateToken() {
        long cur = System.currentTimeMillis();

        long exp = cur + (120 * 1000);

        HashMap<String, Object> map = new HashMap<>();
        map.put("aa","abc");
        map.put("bb","bbf");

        JwtBuilder jwtBuilder = Jwts.builder();
        jwtBuilder.setId("8888")
                .setSubject("Rose")
                // 签发时间
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, "xxxx")
                // 设置过期时间
                .setExpiration(new Date(exp))
                // 自定义
                .addClaims(map);

        String token = jwtBuilder.compact();
        System.out.println(token);
        String[] split = token.split("\\.");
        System.out.println(Base64Codec.BASE64.decodeToString(split[0]));
        System.out.println(Base64Codec.BASE64.decodeToString(split[1]));
        System.out.println(Base64Codec.BASE64.decodeToString(split[2]));
    }

    /**
     * 解析token
     */
    @Test
    public void testParseToken() {

        String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI4ODg4Iiwic3ViIjoiUm9zZSIsImlhdCI6MTYzODExMDQ2OSwiZXhwIjoxNjM4MTEwNTg5LCJhYSI6ImFiYyIsImJiIjoiYmJmIn0.78-vvvuC5Sq3jzMC5znKOBOCRd3tzzI8vJwsmqq6iJ0";

        Claims claims = Jwts.parser()
                .setSigningKey("xxxx")
                .parseClaimsJws(token)
                .getBody();
        System.out.println("id: " + claims.getId());
        System.out.println("subject: " + claims.getSubject());
        System.out.println("issuedit: " + claims.getIssuedAt());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        System.out.println("签发时间： "+simpleDateFormat.format(claims.getIssuedAt()));
        System.out.println("过期时间： "+simpleDateFormat.format(claims.getExpiration()));
        System.out.println("当前时间： "+simpleDateFormat.format(new Date()));
        System.out.println("aa: "+claims.get("aa"));
        System.out.println("bb: "+claims.get("bb"));


    }
}
