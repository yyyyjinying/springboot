package com.zhao.demo.service;

import com.zhao.demo.bean.Users;
import com.zhao.demo.mapper.UsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    UsersMapper usersMapper;

    @Override
    public Users selByUsers(Users users) {
        return usersMapper.selByUsers(users);
    }
}
