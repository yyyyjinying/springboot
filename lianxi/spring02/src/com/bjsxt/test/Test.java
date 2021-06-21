package com.bjsxt.test;

import com.bjsxt.pojo.Airport;
import com.bjsxt.service.impl.AirportServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class Test {
    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        AirportServiceImpl bean = ac.getBean("airportService", AirportServiceImpl.class);
        List<Airport> list = bean.show();
        System.out.println(list);

    }
}
