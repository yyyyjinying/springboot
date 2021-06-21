package com.bjsxt.advice;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class CMyService {
    @Before("com.bjsxt.test.Demo3.demo()")
    public void before(){
        System.out.println("前置");
    }

    @After("com.bjsxt.test.Demo3.demo()")
    public void after(){
        System.out.println("后置");
    }

    @AfterReturning("com.bjsxt.test.Demo3.demo()")
    public void afterReturn(){
        System.out.println("后置return");
    }

    @AfterThrowing("com.bjsxt.test.Demo3.demo()")
    public void afterThrow(){
        System.out.println("throw");
    }


    @Around("com.bjsxt.test.Demo3.demo()")
    public Object arround(ProceedingJoinPoint p) throws Throwable{
        System.out.println("环绕前置");
        Object result = p.proceed();
        System.out.println("环绕后置");
        return result;
    }


}
