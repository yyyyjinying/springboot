package com.cloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "CLOUD-TEST", fallback = PayHystrixServiceFallBack.class)
public interface PayHystrixService {
    @GetMapping("/feign/hys/ok/{id}")
    String paymentInfo_ok(@PathVariable("id") Integer id);

    @GetMapping("/feign/hys/timeout/{id}")
    String paymentInfo_timeout(@PathVariable("id") Integer id);
}
