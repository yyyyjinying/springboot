package com.xxxx.springsecuritydemo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
public class SpringsecurityDemoApplicationTests {

    @Test
    public void contextLoads() {
        BCryptPasswordEncoder ps = new BCryptPasswordEncoder();
        String encode = ps.encode("123");
        System.out.println(encode);
        boolean matches = ps.matches("123", encode);
        System.out.println(matches);


    }

}
