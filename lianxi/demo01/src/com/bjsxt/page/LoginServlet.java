package com.bjsxt.page;

import com.bjsxt.pojo.User;
import com.bjsxt.service.LoginService;
import com.bjsxt.service.impl.LoginServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 设置请求编码utf-8
        req.setCharacterEncoding("utf-8");

        String uname = req.getParameter("uname");
        String pwd = req.getParameter("pwd");

        System.out.println(uname + ":"+pwd);

        LoginService ls = new LoginServiceImpl();

        User u = ls.checkLoginService(uname, pwd);

        System.out.println(u);

        if(u!=null){

//            req.getRequestDispatcher("main").forward(req,resp);
            resp.sendRedirect("/lianxi/demo01/login/main");

        }else{
            req.setAttribute("str", "用户名或者密码错误");
            req.getRequestDispatcher("page").forward(req, resp);
            return;
        }


    }
}
