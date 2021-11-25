package com.cloud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StreamController {
    @GetMapping(value = "/test")
    public String sTest(){
        return "aaaaa";
    }
}
