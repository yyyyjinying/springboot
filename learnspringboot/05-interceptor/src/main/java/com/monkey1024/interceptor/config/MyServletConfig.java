package com.monkey1024.interceptor.config;

import com.monkey1024.interceptor.servlet.MyServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyServletConfig {

    @Bean
    public ServletRegistrationBean myServletRegistration() {
        ServletRegistrationBean regist = new ServletRegistrationBean(new MyServlet());
        regist.addUrlMappings("/myServlet");
        return regist;
    }
}
