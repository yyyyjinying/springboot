package com.bjsxt.service.impl;

import com.bjsxt.mapper.FilesMapper;
import com.bjsxt.pojo.Files;
import com.bjsxt.service.FilesService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class FilesServiceImpl implements FilesService {
    @Resource
    FilesMapper filesMapper;

    @Override
    public List<Files> show(){
        return filesMapper.show();
    }

    @Override
    public int updCount(int id, String name) {
        return filesMapper.updCount(id, name);
    }

}
