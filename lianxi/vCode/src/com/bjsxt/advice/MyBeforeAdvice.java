package com.bjsxt.advice;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

public class MyBeforeAdvice implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] objects, Object o) throws Throwable {
        System.out.println("切入点方法对象："+method+"，方法名"+method.getName());
        if(objects!=null && objects.length > 0){
            System.out.println("切点方法参数:"+objects);

        } else {
            System.out.println("没有切点参数");
        }
        System.out.println("对象："+o);
        System.out.println("执行前置通知");
    }
}
