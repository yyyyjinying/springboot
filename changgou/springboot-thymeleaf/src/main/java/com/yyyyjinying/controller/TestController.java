package com.yyyyjinying.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * thymeleaf模版引擎
 */
@Controller
@RequestMapping(value = "/test")
public class TestController {

    @RequestMapping(value = "/hello")
    public String hello(Model model) {
        model.addAttribute("message","hello thymeleaf!");
        return "demo1";
    }

}
