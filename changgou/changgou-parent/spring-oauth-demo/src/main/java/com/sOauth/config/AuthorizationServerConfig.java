package com.sOauth.config;

import com.netflix.discovery.converters.Auto;
import com.sOauth.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.support.collections.RedisStore;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.ArrayList;

/**
 * 授权服务器
 * 1。客户端先访问授权服务器返回授权码
 * 2。拿着授权码再去授权服务器获取登录令牌|刷新令牌
 * 3。拿着登录令牌去资源服务器获取用户信息
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserServiceImpl userServiceImpl;


    @Autowired
//    @Qualifier("jwtTokenStore")
    private TokenStore tokenStore;

    @Autowired
    private JwtAccessTokenConverter jwtAccessTokenConverter;

    @Autowired
    private JwtTokenEnhancer jwtTokenEnhancer;

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        // 必须要身份认证 单点登录必须要配置
        security.tokenKeyAccess("isAuthenticated()");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                // 客户端ID
                .withClient("client")
                // 密钥
                .secret(passwordEncoder.encode("112233"))
                // 重定向地址
//                .redirectUris("http://www.baidu.com")
                .redirectUris("http://localhost:8051/login")
                // 授权范围
                .scopes("all")
                /** 授权类型
                 * authorization_code： 授权码模式
                 * password：密码模式
                 */
                .authorizedGrantTypes("authorization_code","password","refresh_token")
                // 设置令牌失效时间 60秒
                .accessTokenValiditySeconds(60)
                // 刷新令牌失效时间
                .refreshTokenValiditySeconds(8000)
                // 是否自动授权
                .autoApprove(true);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        // 设置jwt的增强内容
        TokenEnhancerChain chain = new TokenEnhancerChain();
        ArrayList<TokenEnhancer> delegates = new ArrayList<>();
        delegates.add(jwtTokenEnhancer);
        delegates.add(jwtAccessTokenConverter);
        chain.setTokenEnhancers(delegates);


        endpoints.authenticationManager(authenticationManager)
                .userDetailsService(userServiceImpl)
                .tokenStore(tokenStore)
                .accessTokenConverter(jwtAccessTokenConverter)
                .tokenEnhancer(chain);
    }


}
