package com.zhao.demo.service;

import com.zhao.demo.bean.User;

import java.util.List;

public interface UserService {
    List<User> selectAllUser();
    User selectUserById(int id);
    int addOrUpdate(User user);
    int updateToUser(User user);
    int del(Integer id);
}
