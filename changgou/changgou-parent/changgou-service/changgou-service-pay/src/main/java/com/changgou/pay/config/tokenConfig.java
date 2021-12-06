package com.changgou.pay.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

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
//
//    @Bean
//    TokenEnhancerConfig tokenEnhancerConfig(){
//        return new TokenEnhancerConfig();
//    }
}
