package com.bjsxt.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/")
public class DispatchServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String result = req.getParameter("control");
        System.out.println("aa:"+result);
        if(result.equals("demo1")){
            demo1();
        }
        if(result.equals("demo2")){
            demo2();
        }
        if(result.equals("demo3")){
            demo3();
        }
        System.out.println("执行控制器");


    }

    public void demo1(){
        System.out.println("demo1被调用");
    }
    public void demo2(){
        System.out.println("demo2被调用");
    }
    public void demo3(){
        System.out.println("demo3被调用");
    }
}
