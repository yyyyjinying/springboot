package com.cloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope // 该配置可以手动刷新后生效  curl -X POST "http://localhost:18041/actuator/refresh"
public class ConfigClientController {
//    @Value("${aa.bb}")
    @Value("${config.info}")
    private String info;

    @GetMapping("/info")
    public String getConfigInfo(){
        return info;
    }
}
