package com.cloud.controller;

import com.cloud.service.PayHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod") // 默认是没有参数的
public class PayFeignController {

    @Autowired
    PayHystrixService payHystrixService;


    @GetMapping("/hystrix/ok/{id}")
    public String paymentInfo_ok(@PathVariable("id") Integer id){
       return payHystrixService.paymentInfo_ok(id);
    }

//    @HystrixCommand(fallbackMethod = "paymentInfo_timeoutHandler", commandProperties = {
//            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value="1500")
//    })
    @GetMapping("/hystrix/timeout/{id}")
    @HystrixCommand
    public String paymentInfo_timeout(@PathVariable("id") Integer id){
        int age = 10/0;
        return payHystrixService.paymentInfo_timeout( id);
    }

    /**
     * 服务降级
     * @param id
     * @return
     */
    public String paymentInfo_timeoutHandler(Integer id){
        return "client-线程池-test-01" + Thread.currentThread().getName() + "  系统繁忙或者运行报错，请稍后再试,id: " + id + "\t" + "aiaiai~~~";
    }


    public String payment_Global_FallbackMethod(){
        return "global___系统繁忙或者运行报";
    }



}
