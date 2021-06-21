package com.bjsxt.test;

import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
public class Demo3 {
    @Pointcut("execution(* com.bjsxt.test.Demo3.demo())")
    public void demo(){
        System.out.println(121);
    }

}
