package com.xxxx.springsecuritydemo;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {
//    @RequestMapping("/logins")
//    public String login(){
//        return "redirect:main.html";
//    }

//    @Secured("ROLE_abc")
    @PreAuthorize("hasRole('ROLE_abc')")
    @RequestMapping("/toMain")
    public String toMain(){
        return "redirect:main.html";
    }

    @RequestMapping("/toError")
    public String toError(){
        return "redirect:error.html";
    }

    @GetMapping("/demo")
    @ResponseBody
    public String demo(){
        return "demo";
    }




}
