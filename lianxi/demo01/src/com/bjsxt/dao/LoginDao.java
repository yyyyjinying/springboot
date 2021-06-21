package com.bjsxt.dao;

import com.bjsxt.pojo.User;

public interface LoginDao {

    User checkLoginDao(String uname,String pwd);

    User checkUidDao(String uid);

}
