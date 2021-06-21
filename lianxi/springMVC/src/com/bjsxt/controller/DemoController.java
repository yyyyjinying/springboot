package com.bjsxt.controller;

import com.bjsxt.pojo.Demo;
import com.bjsxt.pojo.ListDemo;
import com.bjsxt.pojo.Users;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.misc.Request;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@Controller
public class DemoController {

    @RequestMapping("demo")
    public String demo(@RequestParam(required=true) String username1, @RequestParam("password1") int password){
        System.out.println("执行demo");
        System.out.println(username1+":"+password);
        return "main.jsp";
    }

    @RequestMapping("demo1")
    public String demo1(Users users,HttpServletRequest req){
        System.out.println("users:"+users);

        req.setAttribute("demo123",users.getUsername());

        return "main.jsp";
    }

    @RequestMapping("page")
    public String page(@RequestParam(defaultValue = "zhao",required=true) String name){
        System.out.println("name="+name);
        return "main.jsp";
    }

    @RequestMapping("demo2")
    public String demo2(String username1, int password1, @RequestParam("hover") List<String> hover){
        System.out.println(username1 + "  " + password1 + "   " + hover);
        return "main.jsp";
    }

    @RequestMapping("demo3")
    public String demo3(Demo demo){
        System.out.println(demo);

        return "main.jsp";
    }

    @RequestMapping("demo4")
    public String demo3(ListDemo list){
        System.out.println(list);

        return "main.jsp";
    }

    @RequestMapping("demo5")
    public String demo5(int id, String name){
        System.out.println(id+"   "+name);
        return "main.jsp";
    }

    @RequestMapping("demo6/{id}/{name}")
    public String demo6(@PathVariable("id") int id1,@PathVariable("name") String name){
        System.out.println(id1 + "    " + name);
        return "/main.jsp";
    }

    @RequestMapping("demo7")
    public String demo7(){

        System.out.println("转发");
        return "/main.jsp";

//        System.out.println("重定向");
//        return "redirect:/main.jsp";
    }

    @RequestMapping("demo8")
    public String demo8(){

        return "main";
    }

    @RequestMapping("demo9")
    public String demo9(){
        return "forward:demo8";
    }



    @RequestMapping("demo11")
    @ResponseBody
    public Users demo11() throws IOException {
        Users users = new Users();
        users.setUsername("张三");
        users.setPassword(123456);

        return users;
    }

    @RequestMapping(value="demo10",produces="text/html;charset=utf-8")
    @ResponseBody
    public String demo10(){
        return "招生姐";
    }


}
