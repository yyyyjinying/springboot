package com.zhao.demo.controller;
import com.alibaba.fastjson.JSONObject;
import com.zhao.common.utils.ResponseData;
import com.zhao.demo.bean.Users;
import com.zhao.demo.service.UsersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/users")
@Api(tags = "users列表接口")
public class UsersController {

    @Autowired
    UsersService usersService;


    // http://localhost:9090/users/selByUsers?username=zhao&password=123
    @ApiOperation(value="用户列表-查询参数")
    @GetMapping("/selByUsers")
    public Users selByUsers(@RequestParam("username") String username, @RequestParam("password") String password){
        Users users = new Users();
        users.setUsername(username);
        users.setPassword(password);
        return usersService.selByUsers(users);
    }

    //    http://localhost:9090/users/selByUsers/zhao/123
    @ApiOperation(value="用户列表-路径参数")
    @GetMapping("/selByUsers/{username}/{password}")
    public ResponseData selByVarUsers(@PathVariable("username") String username, @PathVariable("password") String password){
        Users users = new Users();
        users.setUsername(username);
        users.setPassword(password);
        Users data = usersService.selByUsers(users);
        return ResponseData.ok(data);
    }

}
