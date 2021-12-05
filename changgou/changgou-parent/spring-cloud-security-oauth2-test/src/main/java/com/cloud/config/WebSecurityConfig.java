package com.cloud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.InMemoryAuthorizationCodeServices;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    // 重新定义加密算法
    @Bean
    public PasswordEncoder getPW(){
        return new BCryptPasswordEncoder();
    }
//
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public AuthorizationCodeServices authorizationCodeServices() {
        // 存取，暂时采用内存方式
        return new InMemoryAuthorizationCodeServices();
    }



    // command + O快捷键重写方法
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable() // 关闭csrf
//                .authorizeRequests()
//                .anyRequest()
//                .authenticated()// 无需认证
//                .and()
//                .formLogin()
//                .loginPage("/oauth/login") // 重置登录页面
////                .loginProcessingUrl("/user/login") // 登录页面表单的提交地址， 必须和表单提交的接口一样，会去执行自定义登录逻辑
//                .successForwardUrl("/loginSuccess"); // 登录成功后的跳转地址







        http.csrf().disable() // 关闭csrf
            .authorizeRequests()
                .antMatchers("/r/r1").hasAuthority("p1") // p1,p3,p2,ROLE_admin,ROLE_query
                .antMatchers("/r/r2").hasAuthority("p2")
                .antMatchers("/r/r3").access("hasAuthority('p1') and hasAuthority('p2')")
//                .antMatchers("/r/r4").hasRole("admin") // 角色区分大小写
                .antMatchers("/r/**")
                .authenticated() // 满足条件的必须认证
                .anyRequest().permitAll() // 无需认证
                .and()
                .formLogin()
//                .httpBasic()        //启用Http基本身份验证
                .loginPage("/oauth/login") // 重置登录页面
//                .successForwardUrl("/loginSuccess") // 登录成功后跳转地址
//                .loginProcessingUrl("/user/login") // 登录页面表单的提交地址， 必须和表单提交的接口一样，会去执行自定义登录逻辑
                .permitAll();
//                .and()
//                .logout()
//                .logoutUrl("/oauth/logout")
//                .logoutSuccessUrl("/oauth/login");
    }
}
