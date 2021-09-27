package com.sOauth.controller;

import io.jsonwebtoken.Jwts;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/user")
public class UserController {

//一、授权码模式
    /**
     * 获取授权码
     * http://localhost:8012/oauth/authorize?response_type=code&scope=all&client_id=client&redirect_uri=http://www.baidu.com
     * @return code
     */

    /**
     * 获取登录令牌
     * postman
     * http://localhost:8012/oauth/token
     * post请求
     *
     * Athorization
     * basic auth
     * username client
     * password 112233
     *
     *body
     * x-wwww-form-urlencoded
     *1. grant_type authorization_code
     *2. code kd13x5
     *3. client_id client
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
     * 拿到登录令牌请求用户信息
     * postman
     * localhost:8012/user/getCurrentUser
     *
     * post请求
     * authorization
     * type: bearer token
     * Token: 2eb4c537-7451-4824-93d4-d905e86f1662
     * @param authorization
     * @return
     */


    // 二、 密码模式

    /**
     *
     * 直接获取登录令牌，不需要获取授权码
     * postman
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
     *2.scope all
     *3. username admin
     *4. password 1234556
     *
     * @return {
     *     "access_token": "2eb4c537-7451-4824-93d4-d905e86f1662",
     *     "token_type": "bearer",
     *     "expires_in": 43199,
     *     "scope": "all"
     * }
     */
    @RequestMapping("/getCurrentUser")
    public Object getCurrentUser(Authentication authorization){
        return authorization.getPrincipal();


    }

    /**
     * localhost:8012/user/getJwtUser
     * post请求
     * header
     * Authorization: access_token
     * 返回解析jwt后的值
     *
     * return {
     *     "user_name": "admin",
     *     "scope": [
     *         "all"
     *     ],
     *     "exp": 1632750798,
     *     "authorities": [
     *         "admin"
     *     ],
     *     "jti": "de81f108-7805-4c77-a3f5-17a58786c7ee",
     *     "client_id": "client",
     *     "enhance": "enhancer info"
     * }
     */
    @RequestMapping("/getJwtUser")
    public Object getJwtUser(Authentication authentication, HttpServletRequest httpServletRequest){
        String header = httpServletRequest.getHeader("Authorization");
        // bearer最后一次出现的索引 + 7
        String token = header.substring(header.lastIndexOf("bearer") + 7);

        return Jwts.parser()
                .setSigningKey("test_key".getBytes(StandardCharsets.UTF_8))
                .parseClaimsJws(token)
                .getBody();
    }

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
    /**
     * localhost:8012/user/getRefreshToken
     * post请求
     * header
     * Authorization: access_token
     * 返回解析jwt后的值
     *
     * return {
     *     "user_name": "admin",
     *     "scope": [
     *         "all"
     *     ],
     *     "exp": 1632750798,
     *     "authorities": [
     *         "admin"
     *     ],
     *     "jti": "de81f108-7805-4c77-a3f5-17a58786c7ee",
     *     "client_id": "client",
     *     "enhance": "enhancer info"
     * }
     */

    @RequestMapping("/getRefreshToken")
    public Object getRefreshToken(Authentication authentication, HttpServletRequest httpServletRequest){
        String header = httpServletRequest.getHeader("Authorization");
        // bearer最后一次出现的索引 + 7
        String token = header.substring(header.lastIndexOf("bearer") + 7);

        return Jwts.parser()
                .setSigningKey("test_key".getBytes(StandardCharsets.UTF_8))
                .parseClaimsJws(token)
                .getBody();
    }

    @RequestMapping("/test")
    public String test(){
        return "test";

    }

}
