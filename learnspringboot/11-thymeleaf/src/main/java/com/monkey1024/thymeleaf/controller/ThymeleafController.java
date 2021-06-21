package com.monkey1024.thymeleaf.controller;

import com.monkey1024.thymeleaf.bean.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

@Controller
public class ThymeleafController {

    @RequestMapping("/first")
    public String first(Model model, HttpSession session) {
        model.addAttribute("name", "jack");

        User user = new User();
        user.setId(1001);
        user.setName("james");

        model.addAttribute("user", user);

        model.addAttribute("page", 5);

        session.setAttribute("phone","18888888888");

        model.addAttribute("myDate", new Date());

        //视图解析器后缀默认是html
        return "index";
    }

    @RequestMapping("/list")
    public String selectList(Model model) {
        ArrayList<User> list = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setId(i);
            user.setName("jack" + i);
            list.add(user);
        }

        model.addAttribute("list", list);

        return "user";
    }

    @RequestMapping("/map")
    public String selectMap(Model model) {
        HashMap<String, User> map = new HashMap<>();

        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setId(i);
            user.setName("paul" + i);
            map.put(String.valueOf(i), user);
        }

        model.addAttribute("map", map);

        return "user";
    }

    @RequestMapping("/inline")
    public String inline(Model model) {

        model.addAttribute("name", "paul");
        return "index";
    }
}
