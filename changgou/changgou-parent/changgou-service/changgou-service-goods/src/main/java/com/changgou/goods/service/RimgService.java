package com.changgou.goods.service;

import com.changgou.goods.pojo.Rimg;

import java.util.List;

public interface RimgService {
    List<Rimg> selectAll();
    Integer del(Integer id);
}
