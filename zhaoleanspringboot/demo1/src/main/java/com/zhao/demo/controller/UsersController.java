package com.zhao.demo.controller;

import com.zhao.demo.bean.Users;
import com.zhao.demo.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    UsersService usersService;


    // http://localhost:9090/users/selByUsers?username=zhao&password=123
    @GetMapping("/selByUsers")
    public Users selByUsers(@RequestParam("username") String username, @RequestParam("password") String password){
        Users users = new Users();
        users.setUsername(username);
        users.setPassword(password);
        return usersService.selByUsers(users);
    }

    //    http://localhost:9090/users/selByUsers/zhao/123
    @GetMapping("/selByUsers/{username}/{password}")
    public Users selByVarUsers(@PathVariable("username") String username, @PathVariable("password") String password){
        Users users = new Users();
        users.setUsername(username);
        users.setPassword(password);
        return usersService.selByUsers(users);
    }

}
