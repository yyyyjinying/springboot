package com.cloud.controller;

import com.cloud.utils.TokenUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
@RequestMapping(value = "/r")
public class RTestController {

    @GetMapping(value = "/r1")
    @PreAuthorize("hasAnyAuthority('p2')")
    public String r1(){
        Map<String, String> userInfo = TokenUtils.getUserInfo();
        String username = userInfo.get("user_name");
        return username + "认证OK-资源服务器-访问资源1";
    }

    @GetMapping(value = "/r2")
    public String r2(){
        return "认证OK-资源服务器-访问资源2";
    }

}
