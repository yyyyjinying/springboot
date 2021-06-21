package com.bjsxt.service;


import com.bjsxt.pojo.User;

public interface LoginService {

    User checkLoginService(String uname, String pwd);
    User checkUidService(Integer uid);

}
