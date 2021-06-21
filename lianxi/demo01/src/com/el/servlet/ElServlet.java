package com.el.servlet;

import com.el.pojo.Address;
import com.el.pojo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "ElServlet")
public class ElServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        String name = req.getParameter("uname");
        String pwd = req.getParameter("pwd");
        System.out.println(name + ':'+pwd);

        req.setAttribute("str", "今天天气很凉快，适合学习！");

        User u = new User("赵晋英", "拍视频", new Address("河南", "商丘", "陈丞县"));
        req.setAttribute("user", u);

        ArrayList<String> list = new ArrayList<>();
        list.add("张家辉");
        list.add("关小童");
        list.add("刘诗诗");

        req.setAttribute("list", list);

        User u2=new User("古力娜扎","拍电影",new Address("新疆","乌鲁木齐","乌鲁木齐"));
        List<User> list2 = new ArrayList<>();
        list2.add(u2);

        req.setAttribute("list2",list2);

        Map<String, String> map = new HashMap<String, String>();
        map.put("a", "北京");
        map.put("b", "上海");
        map.put("c", "商丘");
        req.setAttribute("map",map);
        //存储对象
        Map<String,User> map2=new HashMap<String,User>();
        map2.put("a1", new User("迪丽热巴","拍电影",new Address("新疆","吐鲁番","吐鲁番")));
        req.setAttribute("map2",map2);
        //空值判断
        req.setAttribute("s","");
        req.setAttribute("s1",new User());
        req.setAttribute("s2",new ArrayList());
        req.setAttribute("s3",new HashMap());



        req.getRequestDispatcher("/el.jsp").forward(req,resp);
    }
}
