package com.zhao.demo.controller;

import com.zhao.demo.bean.User;
import com.zhao.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


//@RestController
@Controller
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/user/lists")
    @ResponseBody
    public List<User> list(){
        return userService.selectAllUser();
    }

    @GetMapping("/user/list")
    public String index(Model model) {
        List<User> users = userService.selectAllUser();
        model.addAttribute("users", users);

        return "index";
    }

    @GetMapping("/user/toAdd")
    public String toAdd(){
        return "user";
    }

    @RequestMapping("/user/toUpdate")
    public String toUpdate(@RequestParam(value = "id") Integer id,Model model) {

        User user = userService.selectUserById(id);

        model.addAttribute("user", user);

        return "user";

    }

//    @RequestMapping(value = "/user/add", method = RequestMethod.POST)
    @PostMapping("/user/add")
    public String add(User user){
        if(user.getId() == null){
            // 不存在ID就是新增
            userService.addOrUpdate(user);
        }else{
            userService.updateToUser(user);
        }

        return "redirect:/user/list";

    }

    @GetMapping("/user/del")
    public String del(@RequestParam("id") Integer id){
        userService.del(id);

        return "redirect:/user/list";
    }

    @GetMapping("/user/selectById")
    @ResponseBody
    public User selectById(@RequestParam(value = "id") Integer id){
        return userService.selectUserById(id);
    }
}
