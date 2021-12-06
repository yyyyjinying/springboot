package com.changgou.order;

import entity.IdWorker;
import interceptor.FeignInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages = {"com.changgou.goods.feign","com.changgou.order.feign"})
@MapperScan(basePackages = {"com.changgou.order.dao"})
public class OrderApplication {

    public static void main(String[] args) {

        SpringApplication.run(OrderApplication.class,args);
    }

    @Bean
    public IdWorker idWorker(){
        return new IdWorker(1,1);
    }

    @Bean
    FeignInterceptor feignInterceptor(){
        return new FeignInterceptor();
    }
}