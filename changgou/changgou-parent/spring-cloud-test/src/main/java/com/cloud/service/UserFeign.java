package com.cloud.service;

import com.changgou.user.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
@FeignClient(value = "USER")
@RequestMapping("/user")
public interface UserFeign {
    @GetMapping(value = "/testLogin/{username}")
    User testLogin(@PathVariable("username") String username);
}
