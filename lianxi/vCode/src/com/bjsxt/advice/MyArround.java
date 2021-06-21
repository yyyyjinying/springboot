package com.bjsxt.advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;


/**
 * 环绕通知
 */
public class MyArround implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation arg0) throws Throwable {
        System.out.println("环绕前置");
        Object result = arg0.proceed();
        System.out.println("环绕后置");
        return result;
    }
}
