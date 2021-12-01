package com.cloud.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.Arrays;

/**
 * 授权服务器配置
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServer extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private PasswordEncoder getPW;
    //
    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private JwtAccessTokenConverter accessTokenConverter;


    @Autowired
    private AuthenticationManager authenticationManagerBean;

    @Autowired
    private AuthorizationCodeServices authorizationCodeServices;
    //
    @Autowired
    private ClientDetailsService clientDetailsService;

    @Bean
    public AuthorizationServerTokenServices tokenService() {
        DefaultTokenServices service = new DefaultTokenServices();
        service.setClientDetailsService(clientDetailsService);
        service.setSupportRefreshToken(true);
        service.setTokenStore(tokenStore);
// 令牌增强
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(accessTokenConverter));
        service.setTokenEnhancer(tokenEnhancerChain);

        service.setAccessTokenValiditySeconds(7200); // 令牌默认有效期2小时
        service.setRefreshTokenValiditySeconds(259200); // 刷新令牌默认有效期3天
        return service;
    }

    /**
     * 用来配置客户端像详情服务
     * 客户端详情信息在这里初始化
     * 可以写死，数据库存储数据信息
     *
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
                // 配置redirect——uri，用于授权成功后跳转
                .redirectUris("http://www.baidu.com")
                // 配置申请的权限范围
                .scopes("all")
                .resourceIds("res1") // 资源列表
                /* 配置grant_type , 表示授权码模式
//               * .authorizedGrantTypes("authorization_code");
                 * authorization_code： 授权码模式
                 * password：密码模式
                 */
                .authorizedGrantTypes("authorization_code", "password", "client_credentials", "implicit", "refresh_token")
                // 配置访问token的有效期
                .accessTokenValiditySeconds(3600)
                // 配置访问刷新令牌有时间
                .refreshTokenValiditySeconds(864000)
                // 是否自动授权
                .autoApprove(true);
    }

    /**
     * 用来配置令牌的访问端点和令牌服务
     *
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//        /** // 设置jwt的增强内容
//         TokenEnhancerChain chain = new TokenEnhancerChain();
//         ArrayList<TokenEnhancer> delegates = new ArrayList<>();
//         delegates.add(tokenEnhancerConfig);
//         delegates.add(jwtAccessTokenConverter);
//         chain.setTokenEnhancers(delegates);
//
//
//         endpoints.authenticationManager(authenticationManagerBean)
//         .userDetailsService(userService)
//         //                .tokenStore(tokenStore); // 授权服务器将token存储到redis中
//         // 配置令牌存储策略
//         .tokenStore(jwtsTokenStore)
//         // 配置令牌相关参数
//         .tokenEnhancer(chain);
//         */
//
        endpoints
                .authenticationManager(authenticationManagerBean) // mim密码模式需要
                .authorizationCodeServices(authorizationCodeServices) // 授权码模式需要
                .tokenServices(tokenService()) // 令牌管理服务
                .allowedTokenEndpointRequestMethods(HttpMethod.POST); // 允许post提交访问令牌
    }

    //
//
//    /**
//     * 用来配置令牌端点的安全约束.
//     *
//     * @param security
//     * @throws Exception
//     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                .tokenKeyAccess("permitAll()") // oauth/tokey_key公开
                .checkTokenAccess("permitAll()") // oauth/check_token 公开
                .allowFormAuthenticationForClients(); // 表单认证，来申请令牌
    }

}
