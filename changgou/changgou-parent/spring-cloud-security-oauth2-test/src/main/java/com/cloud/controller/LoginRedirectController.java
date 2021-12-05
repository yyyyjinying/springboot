package com.cloud.controller;

import entity.Result;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
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
    public String login(HttpServletRequest httpServletRequest, Model model) {
        String url = httpServletRequest.getParameter("url");
        model.addAttribute("from",url);
        return "login";
    }



    @RequestMapping("/demo")
    public String demo() {
        return "demo";
    }


}
