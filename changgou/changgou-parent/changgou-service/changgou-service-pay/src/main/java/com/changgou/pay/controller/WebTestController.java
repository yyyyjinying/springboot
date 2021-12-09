package com.changgou.pay.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebTestController {

    @GetMapping("/aa")
    public String getAA(){
        return "aa";
    }
}
