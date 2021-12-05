package com.cloud.controller;

import entity.Result;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@CrossOrigin
@RequestMapping(value = "/oauth")
public class LoginRedirectController {
    /***
     * 跳转到登录页面
     * @return
     */
    @RequestMapping("/login")
    public String login(String From, Model model) {
        model.addAttribute("from",From);

        return "login";
    }



    @RequestMapping("/demo")
    public String demo() {
        return "demo";
    }


}
