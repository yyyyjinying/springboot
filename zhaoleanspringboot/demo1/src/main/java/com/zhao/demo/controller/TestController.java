package com.zhao.demo.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Value("${school.name}")
    private String name;

    @Value("${school.age}")
    private String age;

    @Value("${school.address}")
    private String address;


//    @RequestMapping("initConfig")
//    @ResponseBody
//    private String initConfig() {
//        return name + "," + address + "," + age;
//    }

    @GetMapping("demo")
    @ResponseBody
    public String demo(){
        System.out.println("++++++---+++");
        return name + "," + address + "," + age;
    }

}
