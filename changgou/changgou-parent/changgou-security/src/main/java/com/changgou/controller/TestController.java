package com.changgou.controller;

import entity.Result;
import entity.StatusCode;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController
//@RequestMapping("/user")
public class TestController {

    @RequestMapping("/test")
    public Result getTest(){
        return new Result(true,StatusCode.OK,"请求成功！");
    }

}
