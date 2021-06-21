package com.monkey1024.jsp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class JspController {

    @GetMapping("/show")
    public String showJsp(Model model) {
        model.addAttribute("msg", "hello");

        return "test";
    }
}
