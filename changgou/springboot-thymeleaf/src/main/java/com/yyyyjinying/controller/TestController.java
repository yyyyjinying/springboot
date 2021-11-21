package com.yyyyjinying.controller;


import com.yyyyjinying.commons.utils.CookieUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * thymeleaf模版引擎
 */
@Controller
@RequestMapping(value = "/test")
public class TestController {

    @RequestMapping(value = "/hello")
    public String hello(Model model) {
        model.addAttribute("message","hello thymeleaf!");
        return "demo1";
    }

    @RequestMapping("/co")
    public String test(HttpServletRequest request, HttpServletResponse response){
        Cookie[] cookies = request.getCookies();
        for (Cookie cookieItem : cookies) {
            if (cookieItem.getName() != "testCookie") {
                Cookie cookie = new Cookie("testCookie", "test");
                cookie.setDomain("test.com");
                cookie.setPath("/");
                cookie.setMaxAge(36000);
                response.addCookie(cookie);
            }
        }



//        String cookieName="custom_global_session_id";
//        String encodeString="UTF-8";
//
//        String cookieValue = CookieUtils.getCookieValue(request, cookieName, encodeString);
//
//        if(null == cookieValue || "".equals(cookieValue.trim())){
//            System.out.println("无cookie，生成新的cookie数据");
//            cookieValue = UUID.randomUUID().toString();
//        }
//
//        // 根据cookieValue访问数据存储，获取客户端数据。
//
//        CookieUtils.setCookie(request, response, cookieName, cookieValue, 0, encodeString);

        return "demo1";
    }

}
