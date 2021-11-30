package com.cloud.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    /**
     * 用户登录成功
     * @return
     */
    @RequestMapping(value = "/loginSuccess")
    public String loginSuccess() {
        String username = getUsername();
        return username + " 登录成功";
    }

    private String getUsername(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!authentication.isAuthenticated()){
            return null;
        }
        Object principal = authentication.getPrincipal();
        String username = null;
        if (principal instanceof org.springframework.security.core.userdetails.UserDetails) {
            username =
                    ((org.springframework.security.core.userdetails.UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        return username;
    }

    /**
     * 测试资源1
     * @return
     */
    @GetMapping(value = "/r/r1")
    public String r1(){
        String username = getUsername();
        return username + " 访问资源1";
    }

    /**
     * 测试资源2
     * @return
     */
    @GetMapping(value = "/r/r2")
    public String r2(){
        String username = getUsername();
        return username + " 访问资源2";
    }

    @GetMapping("/demo")
    public String getDemo() {
        return "demo";
    }
}
