package com.cloud.config;

import com.cloud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.ArrayList;

/**
 * 授权服务器配置
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServiceConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    PasswordEncoder getPW;

    @Autowired
    private AuthenticationManager authenticationManagerBean;

    @Autowired
    private UserService userService;

//    @Autowired
//    @Qualifier("redisTokenStore")
//    private TokenStore tokenStore;

    @Autowired
    @Qualifier("jwtsToken")
    private TokenStore jwtsTokenStore;

    @Autowired
    private JwtAccessTokenConverter jwtAccessTokenConverter;

    @Autowired
    private TokenEnhancerConfig tokenEnhancerConfig;


    /**
     * 用来配置客户端像详情服务
     * 客户端详情信息在这里初始化
     * 可以写死，数据库存储数据信息
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                // 配置client-id
                .withClient("client")
                // 配置client-secret
                .secret(getPW.encode("112233"))
                // 配置访问token的有效期
                .accessTokenValiditySeconds(36000)
                // 配置redirect——uri，用于授权成功后跳转
                .redirectUris("http://www.baidu.com")
                // 配置申请的权限范围
                .scopes("all")
                .resourceIds("resl") // 资源列表
                /* 配置grant_type , 表示授权码模式
//               * .authorizedGrantTypes("authorization_code");
                 * authorization_code： 授权码模式
                 * password：密码模式
                 */
                .authorizedGrantTypes("authorization_code","password","client_credentials","implicit","refresh_token")
                // 设置令牌失效时间 60秒
                .accessTokenValiditySeconds(3600)
                // 刷新令牌失效时间
                .refreshTokenValiditySeconds(864000)
                // 是否自动授权
                .autoApprove(true);
    }

    /**
     * 用来配置令牌的访问端点和令牌服务
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        // 设置jwt的增强内容
        TokenEnhancerChain chain = new TokenEnhancerChain();
        ArrayList<TokenEnhancer> delegates = new ArrayList<>();
        delegates.add(tokenEnhancerConfig);
        delegates.add(jwtAccessTokenConverter);
        chain.setTokenEnhancers(delegates);


        endpoints.authenticationManager(authenticationManagerBean)
                .userDetailsService(userService)
//                .tokenStore(tokenStore); // 授权服务器将token存储到redis中
                // 配置令牌存储策略
                .tokenStore(jwtsTokenStore)
                // 配置令牌相关参数
                .tokenEnhancer(chain);
    }

}
