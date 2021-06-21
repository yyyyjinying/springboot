package com.monkey1024.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 自定义配置类
 */
@ConfigurationProperties(prefix = "monkey1024.user")
public class UserProperties {
    private String name;

    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
