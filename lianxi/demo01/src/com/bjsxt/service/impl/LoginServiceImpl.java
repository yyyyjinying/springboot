package com.bjsxt.service.impl;

import com.bjsxt.dao.LoginDao;
import com.bjsxt.dao.impl.LoginDaoImpl;
import com.bjsxt.pojo.User;
import com.bjsxt.service.LoginService;

public class LoginServiceImpl implements LoginService {

    @Override
    public User checkLoginService(String uname, String pwd) {
        LoginDao ld = new LoginDaoImpl();
        return ld.checkLoginDao(uname, pwd);

    }

    @Override
    public User checkUidService(Integer uid) {
        return null;
    }
}
