package com.bjsxt.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DemoInterceptor implements HandlerInterceptor {
    // 进入控制器之前执行
    // 如果返回false，阻止进入控制器
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        System.out.println("进入控制器之前--"+o);
//        return false;
        return true;
    }


    // 控制器执行完成，进入jsp之前执行
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        System.out.println("进入jsp之前--"+modelAndView.getViewName()+"跳转");
        System.out.println("model的值"+modelAndView.getModel().get("model"));
        String word = modelAndView.getModel().get("model").toString();
        String newWord = word.replace("祖国", "**");
        modelAndView.getModel().put("model", newWord);
//        modelAndView.getModel().put("model","修改后天的model");
        System.out.println("postHandle");
    }

    // jsp执行完成之后
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        System.out.println("进入jsp之后-afterCompletion"+e.getMessage());
    }
}
