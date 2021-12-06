package com.changgou.goods.feign;

import com.changgou.goods.pojo.Sku;
import com.changgou.order.pojo.OrderItem;
import entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @GetMapping("/id/{id}")
    Result<Sku> getId(@PathVariable("id") String id);

    @PostMapping("/decrCount")
    Integer decrCount(@RequestBody OrderItem orderItem);

}