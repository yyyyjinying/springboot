package com.bjsxt.advice;

import org.springframework.aop.ThrowsAdvice;

import java.lang.reflect.Method;


public class MyThrowAdvice implements ThrowsAdvice {

    public void afterThrowing(Exception ex) throws Throwable {
        System.out.println("跑出异常"+ex);

    }

    public void afterThrowing(Method m, Object[] args, Object target, Exception ex) {
        // Do something with all arguments
        System.out.println("异常");
    }
}
