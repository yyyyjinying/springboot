package com.cloud.controller;

import com.changgou.goods.pojo.Brand;
import com.changgou.user.pojo.User;
import com.cloud.service.PayFeignService;
import com.cloud.service.PayHystrixService;
import com.cloud.service.UserFeign;
import entity.Result;
import entity.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/feign")
public class PayFeignController {

    @Autowired
    PayFeignService payFeignService;

    @Autowired
    private UserFeign userFeign;

    @Autowired
    PayHystrixService payHystrixService;


    @GetMapping("/user/{username}")
    public User testLogin(){
        User admin = userFeign.testLogin("admin");
        System.out.println(admin.toString());
        return admin;
    }

    @GetMapping("/find/{id}")
    public Result<Brand> findId(@PathVariable("id") Integer id){
        Result<Brand> brandById = payFeignService.findBrandById(id);
        return new Result<Brand>(true, StatusCode.OK, "feign请求成功",brandById.getData());
    }

    @GetMapping("/hys/ok/{id}")
    public String paymentInfo_ok(@PathVariable("id") Integer id){
        String result = payHystrixService.paymentInfo_ok(id);
        log.info("*********result: "+result);
        return result;
    }

    @GetMapping("/hys/timeout/{id}")
    public String paymentInfo_timeout(@PathVariable("id") Integer id){
        String result = payHystrixService.paymentInfo_timeout( id);
        log.info("*********result: "+result);
        return result;
    }

    @GetMapping("hys/circuit/{id}")
    public String paymentCircuitBreaker(@PathVariable("id") Integer id){
        String result = payHystrixService.paymentCircuitBreaker(id);
        log.info("****result: "+result);
        return result;
    }



}
