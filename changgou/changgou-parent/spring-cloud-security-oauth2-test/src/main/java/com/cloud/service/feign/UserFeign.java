package com.cloud.service.feign;

import com.changgou.user.pojo.User;
import entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Component
@FeignClient(value = "USER")
@RequestMapping("/user")
public interface UserFeign {
    @GetMapping(value = "/testLogin/{username}")
    User testLogin(@PathVariable("username") String username);

    @GetMapping(value = "/premission/{username}")
    Result<List<String>> permissionByUsername(@PathVariable("username") String username);

    @GetMapping(value = "/role/{username}")
    List<String> getRoleByUsername(@PathVariable("username") String username);
}
