package com.changgou.pay;

import interceptor.FeignInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@EnableEurekaClient
public class WeixinPayApplication {
    public static void main(String[] args) {
        SpringApplication.run(WeixinPayApplication.class,args);
    }

    @Bean
    FeignInterceptor feignInterceptor(){
        return new FeignInterceptor();
    }
}
