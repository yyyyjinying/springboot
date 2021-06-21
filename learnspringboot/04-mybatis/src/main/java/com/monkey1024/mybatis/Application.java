package com.monkey1024.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("com.monkey1024.mybatis.mapper")//会扫描该包下的接口
@EnableTransactionManagement //开启事务支持
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
