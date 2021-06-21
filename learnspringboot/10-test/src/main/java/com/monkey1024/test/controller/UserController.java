package com.monkey1024.test.controller;

import com.monkey1024.autoconfigure.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/validate")
    public String validate() {
        boolean validate = userService.validate();

        return validate + "";
    }
}
