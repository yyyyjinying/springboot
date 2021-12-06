package com.changgou.order.feign;
import entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Component
@FeignClient(value = "USER")
@RequestMapping("/user")
public interface UserFeign {
    @GetMapping("/points/add/{points}")
    Result<Integer> addPoints(@PathVariable("points") Integer points);
}
