package com.zhao.demo.service;

import com.zhao.demo.bean.User;
import com.zhao.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional // 开启事务
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public List<User> selectAllUser() {
        return userMapper.selectAllUser();
    }

    @Override
    public User selectUserById(int id) {
        return userMapper.selectUserById(id);
    }

    @Override
    public int addOrUpdate(User user) {

        return userMapper.addOrUpdate(user);
    }

    @Override
    public int updateToUser(User user) {
        return userMapper.updateToUser(user);
    }

    @Override
    public int del(Integer id) {
        return userMapper.del(id);
    }


}
