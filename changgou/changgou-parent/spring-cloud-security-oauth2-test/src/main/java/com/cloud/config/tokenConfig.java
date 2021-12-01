package com.cloud.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import javax.sql.DataSource;

@Configuration
public class tokenConfig {

    private String SIGNING_KEY = "client_key";
    /**
     * 默认的令牌存储方式内存
     * @return
     */
//    @Bean
//    public TokenStore tokenStore() {
//        // 默认的内存普通令牌
//        return new InMemoryTokenStore();
//    }

    @Autowired
    private PasswordEncoder getPW;


    @Bean
    public TokenStore tokenStore() {
        // jwt 令牌存储方案
        return new JwtTokenStore(accessTokenConverter());
    }

    @Bean
    JwtAccessTokenConverter accessTokenConverter(){
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        jwtAccessTokenConverter.setSigningKey(SIGNING_KEY); // 对称密钥，资源服务器使用该密钥来验证
        return jwtAccessTokenConverter;
    }

    @Bean
    public ClientDetailsService clientDetailsService(DataSource dataSource) {
        ClientDetailsService clientDetailsService = new JdbcClientDetailsService(dataSource);
        ((JdbcClientDetailsService)
                clientDetailsService).setPasswordEncoder(getPW);
        return clientDetailsService;
    }

    @Bean
    public AuthorizationCodeServices authorizationCodeServices(DataSource dataSource) {
        return new JdbcAuthorizationCodeServices(dataSource);//设置授权码模式的授权码如何存取
    }
//
//    @Bean
//    TokenEnhancerConfig tokenEnhancerConfig(){
//        return new TokenEnhancerConfig();
//    }
}
