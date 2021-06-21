package com.monkey1024.properties.controller;

import com.monkey1024.properties.config.ConfigInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigInfoController {

    /*
        读取自定义配置的方式一:@Value
     */

    @Value("${school.name}")
    private String name;

    @Value("${school.address}")
    private String address;

    @Value("${school.age}")
    private int age;

    @GetMapping("/init1")
    public String initConfig1() {
        return name + "," + address + "," + age;
    }


    /*
     * 读取自定义配置的方式二:自定义配置类
     */
    @Autowired
    private ConfigInfo configInfo;

    @GetMapping("/init2")
    public String initConfig2() {

        return configInfo.getName();
    }
}
