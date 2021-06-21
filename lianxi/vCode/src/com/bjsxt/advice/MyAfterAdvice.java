package com.bjsxt.advice;

import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

public class MyAfterAdvice implements AfterReturningAdvice {
    @Override
    public void afterReturning(Object o, Method method, Object[] objects, Object o1) throws Throwable {
        /**
         * arg0: 切点方法返回值
         * arg1:切点方法对象
         * arg2:切点方法参数
         * arg3:切点方法所在类的对象
         */
        System.out.println("切入点方法对象："+method+"，方法名"+method.getName());
        System.out.println("切点方法参数:"+objects);
        System.out.println("切点方法的返回值："+o);
        System.out.println("切点方法所在类的对象："+o1);
        System.out.println("执行后置通知");
    }
}
