package com.changgou.goods.feign;

import com.changgou.goods.pojo.Sku;
import entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(value="goods")
@RequestMapping("/sku")
public interface SkuFeign {

    /***
     * 根据审核状态查询Sku
     * @param status
     * @return
     */
//    @GetMapping("/status/{status}")
//    Result<List<Sku>> findByStatus(@PathVariable String status);
    @GetMapping
    Result<List<Sku>> findAll();
}