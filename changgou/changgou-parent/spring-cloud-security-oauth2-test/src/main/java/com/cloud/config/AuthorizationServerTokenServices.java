package com.cloud.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.InMemoryAuthorizationCodeServices;

import javax.sql.DataSource;

//@Configuration
public class AuthorizationServerTokenServices {

//    @Bean
//    public PasswordEncoder getPW() {
//        return ;
//    }
//
//    @Bean
//    public ClientDetailsService clientDetailsService(DataSource dataSource) {
//        ClientDetailsService clientDetailsService = new JdbcClientDetailsService(dataSource);
//        ((JdbcClientDetailsService)
//                clientDetailsService).setPasswordEncoder(getPW());
//        return clientDetailsService;
//    }
//
//    @Bean
//    public AuthorizationCodeServices authorizationCodeServices() { //设置授权码模式的授权码如何
//        // 存取，暂时采用内存方式
//        return new InMemoryAuthorizationCodeServices();
//    }

}
