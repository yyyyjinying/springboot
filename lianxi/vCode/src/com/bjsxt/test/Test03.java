package com.bjsxt.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

public class Test03 {
    public static void main(String[] args) {
      ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
//        String[] names = ac.getBeanDefinitionNames();
//        System.out.println(Arrays.toString(names));

        Demo3 demo3 = ac.getBean("demo3", Demo3.class);
        demo3.demo();

    }
}
