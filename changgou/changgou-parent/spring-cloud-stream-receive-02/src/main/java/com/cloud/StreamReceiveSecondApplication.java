package com.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class StreamReceiveSecondApplication {
    public static void main(String[] args) {
        SpringApplication.run(StreamReceiveSecondApplication.class, args);
    }
}
