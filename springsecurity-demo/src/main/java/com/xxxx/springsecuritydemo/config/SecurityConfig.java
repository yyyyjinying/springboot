package com.xxxx.springsecuritydemo.config;

import com.xxxx.springsecuritydemo.handler.MyAccessDeniedHandler;
import com.xxxx.springsecuritydemo.handler.MyAuthenticationFailureHandler;
import com.xxxx.springsecuritydemo.handler.MyAuthenticationSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//
//    @Bean
//    public PasswordEncoder getPw() {
//        return new BCryptPasswordEncoder();
//    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * ... 将参数结构为数组
     *
     * @param params
     */
    public void aa(String... params) {
        System.out.println("params: " + Arrays.toString(params));
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
                .successForwardUrl("/toMain")
//                .successHandler(new MyAuthenticationSuccessHandler("http://baidu.com"))
//                .successHandler(new MyAuthenticationSuccessHandler("/main.html"))
                .failureForwardUrl("/toError");
//                .failureHandler(new MyAuthenticationFailureHandler("/error.html"));


        // 授权
        http.authorizeRequests()
                // 放行error.html
                .antMatchers("/error.html").permitAll()
                // 放行login.html不需要认证
                .antMatchers("/login.html").permitAll()
                // 放行静态资源文件夹
//                .antMatchers("/images/**/").permitAll()
                // 放行静态资源后缀文件
//                .antMatchers("/**/*.png").permitAll()
//                .regexMatchers(".+[.]png").permitAll()
                // 限定请求方式是否执行认证
//                .regexMatchers(HttpMethod.POST, "/demo").permitAll()
//                .mvcMatchers("/demo").servletPath("/xxx").permitAll()

                // 权限控制
//                .antMatchers("/main1.html").hasAuthority("admin")
//                .antMatchers("/main1.html").hasAnyAuthority("admin","admiN")


                // 基于角色
//                .antMatchers("/main1.html").hasRole("abc")
//                .antMatchers("/main1.html").hasAnyRole("abc","abC")

                // 基于IP
//                .antMatchers("/main1.html").hasIpAddress("127.0.0.1")
//                .antMatchers("/main1.html").access("hasRole('abc')")
                //所有页面必须认证才能访问，必须登录
                .anyRequest().authenticated();
//                .anyRequest().access("@myServiceImpl.hasPermission(request, authentication)");
        // 异常处理
        http.exceptionHandling().accessDeniedHandler(new MyAccessDeniedHandler());

        // 先关闭csrf防护
        http.csrf().disable();
    }
}
