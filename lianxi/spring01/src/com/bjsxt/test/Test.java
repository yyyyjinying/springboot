package com.bjsxt.test;

import com.bjsxt.pojo.People;
import com.bjsxt.pojo.PeopleFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        People people = ac.getBean("peo", People.class);
        System.out.println(people);
//        People peo1 = ac.getBean("peo1", People.class);
//        System.out.println(peo1);

//        String[] names = ac.getBeanDefinitionNames();
//        for(String str:names){
//            System.out.println(str);
//
//        }

    }

}
