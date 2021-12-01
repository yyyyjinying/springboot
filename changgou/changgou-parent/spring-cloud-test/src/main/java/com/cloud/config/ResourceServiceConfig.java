package com.cloud.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;


/**
 * 开启资源服务器配置
 */
//@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ResourceServiceConfig extends ResourceServerConfigurerAdapter {
    public static final String RESOURCE_ID = "res1";

    @Autowired
    private TokenStore tokenStore;


    //资源服务令牌解析服务
    @Bean
    public ResourceServerTokenServices tokenService() {
        //使用远程服务请求授权服务器校验token,必须指定校验token 的url、client_id，client_secret
        RemoteTokenServices service = new RemoteTokenServices();
        service.setCheckTokenEndpointUrl("http://localhost:6010/oauth/check_token");
        service.setClientId("client");
        service.setClientSecret("112233");
        return service;
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId(RESOURCE_ID)
//                .tokenServices(tokenService()) //远程校验token令牌
                .tokenStore(tokenStore)
                .stateless(true);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/**").access("#oauth2.hasScope('all')")
                .and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//        http.csrf().disable()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .authorizeRequests()
//                .antMatchers("/**")
//                .access("#oauth2.hasScope('all')")
//                // .antMatchers("/r/r1").hasAuthority("p2")
//                 .antMatchers("/r/r2").hasAuthority("p2")
//                .antMatchers("/r/**").authenticated()//所有/r/**的请求必须认证通过
//                .anyRequest().permitAll();//除了/r/**，其它的请求可以访问


    }
}
