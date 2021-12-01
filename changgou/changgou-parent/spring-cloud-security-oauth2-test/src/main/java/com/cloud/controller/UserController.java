package com.cloud.controller;

import io.jsonwebtoken.Jwts;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/user")
public class UserController {
    //一、授权码模式
    /**
     *第一步： 用户向授权服务器申请授权-》 获取授权码
     * 去授权服务器获取授权码
     * http://localhost:6010/oauth/authorize?response_type=code&scope=all&client_id=client&redirect_uri=http://www.baidu.com
     * @return https://www.baidu.com/?code=CqiP08
     */

    /**
     * 第二步：拿授权码向授权服务器获取登录令牌-》获取登录令牌
     * postman
     * http://localhost:6010/oauth/token
     * post请求
     *
     *body
     * x-wwww-form-urlencoded
     *1. grant_type authorization_code
     *2. code kd13x5
     *3. client_id client
     * client_secret 112233
     *4. redirect_uri http://www.baidu.com
     *5.  scope all
     *
     *
     * @return {
     *     "access_token": "2eb4c537-7451-4824-93d4-d905e86f1662",
     *     "token_type": "bearer",
     *     "expires_in": 43199,
     *     "scope": "all"
     * }
     */

    /**
     *第三步： 拿到登录令牌向资源服务器请求资源-》校验令牌的合法性-请求用户信息
     * postman
     * localhost:6010/user/getCurrentUser
     *
     * post请求
     * authorization
     * type: bearer token
     * Token: 2eb4c537-7451-4824-93d4-d905e86f1662
     * @return
     */

    /**
     * 简化模式 response_type=token
     * http://localhost:6010/oauth/authorize?response_type=token&scope=all&client_id=client&redirect_uri=http://www.baidu.com
     * 访问直接返回：
     * https://www.baidu.com/#access_token=ad2b8639-9eb4-49f5-8115-6d3171dec62c&token_type=bearer&expires_in=3599
     */

    /**
     * 客户端模式
     * post
     * http://localhost:6010/oauth/token
     *
     * x-wwww-form-urlencoded
     *
     * params:
     * grant_type client_credentials
     * client_id client
     * client_secret 112233
     */


    // 二、 密码模式

    /**
     * 第一步： 向授权服务器发请求直接获取登录令牌，省掉了获取授权码
     * postman
     * post请求
     * <p>
     * http://localhost:6010/oauth/token
     * <p>
     * Athorization
     * basic auth
     * username client
     * password 112233
     * <p>
     * body
     * x-wwww-form-urlencoded
     * 1. grant_type password
     * 2.scope all
     * 3. username admin
     * 4. password 1234556
     *
     * @return 资源信息
     */
    /**
     *  获取刷新令牌
     * post请求
     *
     * http://localhost:8012/oauth/token
     *
     * Athorization
     * basic auth
     * username client
     * password 112233
     *
     *body
     * x-wwww-form-urlencoded
     *1. grant_type password
     *2. scope all
     *3. username admin
     *4. password 1234556
     * return {
     *     "access_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJhZG1pbiIsInNjb3BlIjpbImFsbCJdLCJleHAiOjE2MzI3MDg0NDgsImF1dGhvcml0aWVzIjpbImFkbWluIl0sImp0aSI6IjhhZDFiNDliLWM4YjEtNDVjYi04YTE2LTFjYTA2MjVhMjM1YSIsImNsaWVudF9pZCI6ImNsaWVudCIsImVuaGFuY2UiOiJlbmhhbmNlciBpbmZvIn0.X1y9WVySHYEJb7RV5KzO-yhyWxA5MD-yUp0c2NRYV_w",
     *     "token_type": "bearer",
     *     "refresh_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJhZG1pbiIsInNjb3BlIjpbImFsbCJdLCJhdGkiOiI4YWQxYjQ5Yi1jOGIxLTQ1Y2ItOGExNi0xY2EwNjI1YTIzNWEiLCJleHAiOjE2MzI3MTYzODgsImF1dGhvcml0aWVzIjpbImFkbWluIl0sImp0aSI6ImVhNTc0ZTU4LWY1NzItNDdhNy04MjFkLTA0MzI1NTc0OTUwMSIsImNsaWVudF9pZCI6ImNsaWVudCIsImVuaGFuY2UiOiJlbmhhbmNlciBpbmZvIn0.jmnBvLyg0sOgVzPOkmrfqad2sSZmAY2xtpSmKzmhDSI",
     *     "expires_in": 59,
     *     "scope": "all",
     *     "enhance": "enhancer info",
     *     "jti": "8ad1b49b-c8b1-45cb-8a16-1ca0625a235a"
     * }
     *
     * /


     /**
     * 再次使用刷新令牌去获取登录令牌
     *  * post请求
     * post请求
     *
     * http://localhost:8012/oauth/token
     *
     * Athorization
     * basic auth
     * username client
     * password 112233
     *
     *body
     * grant_type: refresh_token
     * refresh_token: "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJhZG1pbiIsInNjb3BlIjpbImFsbCJdLCJhdGkiOiI4"
     *
     * return {
     *     "access_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJhZG1pbiIsInNjb3BlIjpbImFsbCJdLCJleHAiOjE2MzI3MDg0NDgsImF1dGhvcml0aWVzIjpbImFkbWluIl0sImp0aSI6IjhhZDFiNDliLWM4YjEtNDVjYi04YTE2LTFjYTA2MjVhMjM1YSIsImNsaWVudF9pZCI6ImNsaWVudCIsImVuaGFuY2UiOiJlbmhhbmNlciBpbmZvIn0.X1y9WVySHYEJb7RV5KzO-yhyWxA5MD-yUp0c2NRYV_w",
     *     "token_type": "bearer",
     *     "refresh_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJhZG1pbiIsInNjb3BlIjpbImFsbCJdLCJhdGkiOiI4YWQxYjQ5Yi1jOGIxLTQ1Y2ItOGExNi0xY2EwNjI1YTIzNWEiLCJleHAiOjE2MzI3MTYzODgsImF1dGhvcml0aWVzIjpbImFkbWluIl0sImp0aSI6ImVhNTc0ZTU4LWY1NzItNDdhNy04MjFkLTA0MzI1NTc0OTUwMSIsImNsaWVudF9pZCI6ImNsaWVudCIsImVuaGFuY2UiOiJlbmhhbmNlciBpbmZvIn0.jmnBvLyg0sOgVzPOkmrfqad2sSZmAY2xtpSmKzmhDSI",
     *     "expires_in": 59,
     *     "scope": "all",
     *     "enhance": "enhancer info",
     *     "jti": "8ad1b49b-c8b1-45cb-8a16-1ca0625a235a"
     * }
     */


    @RequestMapping("/getCurrentUser")
    public Object getCurrentUser(Authentication authorization) {


        return authorization.getPrincipal();
    }

    /**
     * localhost:8012/user/getHeaderToken
     * post请求
     * header
     * Authorization: access_token
     * 返回解析jwt后的值
     *
     * return {
     * "user_name": "admin",
     * "scope": [
     * "all"
     * ],
     * "exp": 1638199952,
     * "aaaa": "abckefj",
     * "authorities": [
     * "admin"
     * ],
     * "jti": "44f31f4a-1b6f-4c52-a65d-67ffc465cafe",
     * "client_id": "client"
     * }
     */
    @RequestMapping("/getHeaderToken")
    public Object gethTooken(Authorization authorization, HttpServletRequest httpServletRequest) {

        String header = httpServletRequest.getHeader("Authorization");
        String token = header.substring(header.indexOf("bearer") + 7);

        return Jwts.parser().setSigningKey("client_key".getBytes(StandardCharsets.UTF_8)).parseClaimsJws(token).getBody();
    }

    @GetMapping("/demo")
    public String getDemo() {
        return "demo";
    }
}
