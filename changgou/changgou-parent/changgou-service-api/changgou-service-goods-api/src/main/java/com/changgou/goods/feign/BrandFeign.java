package com.changgou.goods.feign;

import com.changgou.goods.feign.config.FeignConfig;
import com.changgou.goods.pojo.Brand;
import entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 测试feign的相关功能
 */
@FeignClient(value="goods")
@RequestMapping("/brand")
public interface BrandFeign {

    //    /brand/1115
    @GetMapping("/feign/{id}")
    Result<Brand> findBrandById(@PathVariable("id") Integer id);
}
