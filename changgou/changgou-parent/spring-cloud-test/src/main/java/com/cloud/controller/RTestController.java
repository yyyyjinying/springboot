package com.cloud.controller;

import com.alibaba.fastjson.JSON;
import com.cloud.utils.TokenUtils;
import entity.JsonUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.MacSigner;
import org.springframework.security.jwt.crypto.sign.RsaVerifier;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/r")
public class RTestController {

    @GetMapping(value = "/r1")
    @PreAuthorize("hasAnyAuthority('p2')")
    public String r1(){
        Map<String, String> userInfo = new TokenUtils().getUserInfo();
        String username = (String) userInfo.get("user_name");
        return username + "认证OK-资源服务器-访问资源1";
    }

    @GetMapping(value = "/r2")
    public String r2(){
        return "认证OK-资源服务器-访问资源2";
    }

}
