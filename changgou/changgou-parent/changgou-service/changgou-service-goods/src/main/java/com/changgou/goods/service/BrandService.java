package com.changgou.goods.service;

import com.changgou.goods.pojo.Brand;

import java.util.List;

public interface BrandService {
    List<Brand> findAll();
    Brand findById(Integer id);
    // 新增
    void add(Brand brand);
    // 修改
    void update(Brand brand);
    // 删除
    void delete(Integer id);


}
