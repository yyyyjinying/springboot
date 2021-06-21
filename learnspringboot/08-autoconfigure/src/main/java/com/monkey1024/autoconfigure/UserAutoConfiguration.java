package com.monkey1024.autoconfigure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自动配置类
 */
@Configuration
@ConditionalOnWebApplication//在web应用下启用
@EnableConfigurationProperties(UserProperties.class)
public class UserAutoConfiguration {

    @Autowired
    private UserProperties userProperties;

    @Bean
    @ConditionalOnMissingBean(UserService.class)//当容器不存在该对象的时候创建
    public UserService addUserService(){
        return new UserService(userProperties);
    }
}
