package com.bjsxt.service.impl;

import com.bjsxt.mapper.RusersMapper;
import com.bjsxt.pojo.Rusers;
import com.bjsxt.service.RusersService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RusersServiceImpl implements RusersService {
    @Resource
    RusersMapper rusersMapper;

    @Override
    public int insRegister(Rusers rusers) {
        return rusersMapper.insRusers(rusers);
    }
}
