package com.xxxx.springsecuritydemo.config;

import com.xxxx.springsecuritydemo.handler.MyAuthenticationSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Bean
    public PasswordEncoder getPw() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 表单提交
        http.formLogin()
                // 自定义用户名和密码
                .usernameParameter("username123")
                .passwordParameter("password123")
                // 自定义登录页面
                .loginPage("/login.html")
                // 必须和表单提交的接口一样，会去执行自定义登录逻辑
                .loginProcessingUrl("/login")
                // 登录成功后的跳转页面
//                .successForwardUrl("/toMain")
//                .successHandler(new MyAuthenticationSuccessHandler("http://baidu.com"))
                .successHandler(new MyAuthenticationSuccessHandler("/main.html"))
                .failureForwardUrl("/toError");



        // 授权
        http.authorizeRequests()
                // 放行error.html
                .antMatchers("/error.html").permitAll()
                // 放行login.html不需要认证
                .antMatchers("/login.html").permitAll()
                //所有页面必须认证才能访问，必须登录
                .anyRequest().authenticated();

        // 先关闭csrf防护
        http.csrf().disable();
    }
}
