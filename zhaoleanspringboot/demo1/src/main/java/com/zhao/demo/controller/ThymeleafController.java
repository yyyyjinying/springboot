package com.zhao.demo.controller;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ThymeleafController {

    @GetMapping("/hello")
    public String helloThymeleaf(Model model) {
        model.addAttribute("name", "jack");
        return "index";
    }

}
