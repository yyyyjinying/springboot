package com.bjsxt.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

@WebServlet(name = "RequestServlet")
public class RequestServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getMethod();
        System.out.println(method);

        String requestURI = req.getRequestURI();
        System.out.println("统一资源标示符："+requestURI);

        StringBuffer requestURL = req.getRequestURL();
        System.out.println("统一资源定位符："+requestURL);

        String scheme = req.getScheme();
        System.out.println("链接使用的协议"+scheme);

        String aaa = req.getHeader("aaa");
        System.out.println("请求头字段："+aaa);

        String header1 = req.getHeader("Accept-Encoding");
        System.out.println("正确的请求header:"+header1);

        Enumeration<String> e = req.getHeaderNames();
        while(e.hasMoreElements()){
            String s = e.nextElement();
            String v = req.getHeader(s);
            System.out.println("键值对："+s+":"+v);
        }

        String uname = req.getParameter("uname");
        String pwd = req.getParameter("pwd");
        System.out.println("parameter:"+uname+"=>"+pwd);

        String[] favs = req.getParameterValues("fav");
        if(favs!=null){
            for(String v:favs){
                System.out.println(v);
            }

        }


    }
}
