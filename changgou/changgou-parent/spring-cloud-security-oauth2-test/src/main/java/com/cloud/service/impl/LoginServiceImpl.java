package com.cloud.service.impl;

import com.cloud.service.LoginService;
import com.cloud.utils.AuthToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;
import java.util.Map;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Override
    public AuthToken login(String username, String password, String scopeValue, String clientId, String clientSecret, String grandType) {

        //1.定义url (申请令牌的url)
        //参数 : 微服务的名称spring.appplication指定的名称
        ServiceInstance choose = loadBalancerClient.choose("demosoauth");
        String url =choose.getUri().toString()+"/oauth/token";

        // 设置请求头
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/x-www-form-urlencoded");

        //2.定义头信息 (有client id 和client secr)
//        MultiValueMap<String,String> headers = new LinkedMultiValueMap<>();
//        headers.add("Authorization","Basic "+Base64.getEncoder().encodeToString(new String(clientId+":"+clientSecret).getBytes()));
        //3. 定义请求体  有授权模式 用户的名称 和密码
        MultiValueMap<String,String> formData = new LinkedMultiValueMap<>();
        formData.add("client_id",clientId);
        formData.add("client_secret",clientSecret);
        formData.add("grant_type",grandType);
        formData.add("username",username);
        formData.add("password",password);
        formData.add("scope",scopeValue);
        //4.模拟浏览器 发送POST 请求 携带 头 和请求体 到认证服务器

        /**
         * 参数1  指定要发送的请求的url
         * 参数2  指定要发送的请求的方法 PSOT
         * 参数3 指定请求实体(包含头和请求体数据)
         */
        HttpEntity<MultiValueMap> requestentity = new HttpEntity<MultiValueMap>(formData,headers);

        ResponseEntity<Map> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestentity, Map.class);
        //5.接收到返回的响应(就是:令牌的信息)
        Map body = responseEntity.getBody();

        //封装一次.

        AuthToken authToken = new AuthToken();
        //访问令牌(jwt)
        String accessToken = (String) body.get("access_token");
        //刷新令牌(jwt)
        String refreshToken = (String) body.get("refresh_token");
        String scope = (String) body.get("scope");
        //jti，作为用户的身份标识
        String jwtToken= (String) body.get("jti");


        authToken.setJti(jwtToken);
        authToken.setAccessToken(accessToken);
        authToken.setRefreshToken(refreshToken);
        authToken.setScope(scope);


        //6.返回
        return authToken;
    }
}
