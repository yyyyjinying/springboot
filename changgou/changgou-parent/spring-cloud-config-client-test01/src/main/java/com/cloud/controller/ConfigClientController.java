package com.cloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigClientController {
//    @Value("${aa.bb}")
    @Value("${config.info}")
    private String info;

    @GetMapping("/info")
    public String getConfigInfo(){
        return info;
    }
}
