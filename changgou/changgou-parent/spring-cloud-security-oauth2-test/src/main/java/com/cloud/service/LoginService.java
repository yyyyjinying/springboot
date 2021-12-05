package com.cloud.service;
import com.cloud.utils.AuthToken;

public interface LoginService {
    /**
     * 模拟用户的行为 发送请求 申请令牌 返回
     * @param username
     * @param password
     * @param scopeValue
     * @param clientId
     * @param clientSecret
     * @param grandType
     * @return
     */
    AuthToken login(String username, String password, String scopeValue, String clientId, String clientSecret, String grandType);

}
