package com.bjsxt.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

public class Test01 {
    public static void main(String[] args) {
      ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        String[] names = ac.getBeanDefinitionNames();
        System.out.println(Arrays.toString(names));

        Demo3 demo3 = ac.getBean("demo3", Demo3.class);
        demo3.demo();
//        Demo demo = ac.getBean("demo", Demo.class);
////        demo.demo1("zhao");
//        try {
//            demo.demo2("zhao",12);
//            demo.demo2("jin");
//        } catch (Exception e) {
////            e.printStackTrace();
//        }

//        Demo1 demo1 = ac.getBean("demo1", Demo1.class);
//        demo1.demo1("jin");
//        demo1.demo2();

    }
}
