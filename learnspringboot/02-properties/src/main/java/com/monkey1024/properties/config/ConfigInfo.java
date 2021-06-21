package com.monkey1024.properties.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 配置类
 */
@Component//会将该类的对象加入到spring容器中
@PropertySource(value = "classpath:school.properties")//制定外部配置文件的名字
@ConfigurationProperties(prefix = "school")//表示会从配置文件中读取一school开头的内容
public class ConfigInfo {

    //对应配置文件中的school.name上的name
    private String name;

    private String address;

    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
