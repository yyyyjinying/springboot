package com.bjsxt.servlet;

import com.bjsxt.pojo.Users;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet("/ajax")
public class AjaxServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("执行控制器");
        String name = req.getParameter("name");
        Users users = new Users();
        users.setId(3);
        users.setPassword("5555");
        users.setUsername("wuwuw");

        ArrayList<Users> list = new ArrayList<>();
        list.add(users);

        ObjectMapper mapper = new ObjectMapper();
        String result = mapper.writeValueAsString(list);
        resp.setContentType("application/json;charset=utf-8");

        PrintWriter out = resp.getWriter();
        out.println(result);
        out.flush();
        out.close();


    }
}
