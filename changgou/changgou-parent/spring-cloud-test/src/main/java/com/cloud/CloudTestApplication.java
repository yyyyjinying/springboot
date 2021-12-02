package com.cloud;

import com.cloud.interceptor.FeignInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableCircuitBreaker //激活
public class CloudTestApplication {
    public static void main(String[] args) {

        SpringApplication.run(CloudTestApplication.class, args);
    }

    /***
     * 创建拦截器Bean对象
     * @return
     */
    @Bean
    FeignInterceptor feignInterceptor(){
        return new FeignInterceptor();
    }

}
