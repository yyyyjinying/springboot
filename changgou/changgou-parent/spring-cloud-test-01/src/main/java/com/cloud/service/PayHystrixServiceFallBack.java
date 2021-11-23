package com.cloud.service;

import org.springframework.stereotype.Component;

@Component
public class PayHystrixServiceFallBack implements PayHystrixService {
    @Override
    public String paymentInfo_ok(Integer id) {
        return "feigin--ok--服务降级";
    }

    @Override
    public String paymentInfo_timeout(Integer id) {
        return "feigin--timeout--服务降级";
    }
}
