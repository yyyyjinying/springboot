package com.cloud.service;

import com.changgou.goods.pojo.Brand;
import entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
@FeignClient(value = "GOODS")
@RequestMapping("/brand")
public interface PayFeignService {
    //    /brand/1115
    @GetMapping("/feign/{id}")
    Result<Brand> findBrandById(@PathVariable("id") Integer id);
}
