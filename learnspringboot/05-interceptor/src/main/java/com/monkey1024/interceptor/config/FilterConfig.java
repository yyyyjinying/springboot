package com.monkey1024.interceptor.config;

import com.monkey1024.interceptor.filter.MyFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 过滤器配置类
 */
@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean myFilterRegistration() {
        FilterRegistrationBean regist = new FilterRegistrationBean(new MyFilter());
        //过滤全部请求
        regist.addUrlPatterns("/*");
        return regist;
    }
}
