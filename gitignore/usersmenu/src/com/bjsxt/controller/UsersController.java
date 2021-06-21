package com.bjsxt.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bjsxt.pojo.Users;
import com.bjsxt.service.UsersService;

@Controller
public class UsersController {
	@Resource
	private UsersService usersServiceImpl;
	@RequestMapping("login")
	public String login(Users users,HttpSession session){
		Users user = usersServiceImpl.login(users);
		if(user!=null){
			session.setAttribute("user", user);
			return "redirect:/main.jsp";
		}else{
			return "redirect:/index.jsp";
		}
	}
}
