package com.changgou.order.utils;

import com.alibaba.fastjson.JSON;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.MacSigner;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

import java.util.Map;

public class TokenUtils {

    private String SIGNING_KEY = "client_key";

    /***
     * 读取令牌数据
     */
    public static Map<String,String> dcodeToken(String token){
        //校验Jwt
//        Jwt jwt = JwtHelper.decodeAndVerify(token, new RsaVerifier(SIGNING_KEY));

        //获取Jwt原始内容
        Jwt jwt = JwtHelper.decodeAndVerify(token, new MacSigner("client_key"));
        String claims = jwt.getClaims();
        return JSON.parseObject(claims, Map.class);
    }

    /***
     * 获取用户信息
     * @return
     */
    public static Map<String,String> getUserInfo(){
        //获取授权信息
        OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
        //令牌解码
        return dcodeToken(details.getTokenValue());
    }
}
