package com.zhao.demo.config;

import com.zhao.demo.servlet.MyServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletRegistration;

@Configuration
public class MyServletConfig {
    @Bean
    public ServletRegistrationBean myServletRegistration(){
        ServletRegistrationBean regist = new ServletRegistrationBean(new MyServlet());
        regist.addUrlMappings("/myServlet");
        return regist;
    }

}
