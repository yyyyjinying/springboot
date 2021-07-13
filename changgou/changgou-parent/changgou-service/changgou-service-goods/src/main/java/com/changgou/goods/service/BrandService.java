package com.changgou.goods.service;

import com.changgou.goods.pojo.Brand;
import com.github.pagehelper.PageInfo;
import entity.Page;

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

    /**
     * 根据条件查询品牌列表
     *
     */
    List<Brand> findList(Brand brand);

    /**
     * 分页查
     * @param page
     * @param size
     * @return
     */
    PageInfo<Brand> findPage(Integer page, Integer size);


    /**
     * 条件分页查询
     * @param page
     * @param size
     * @param brand
     * @return
     */
    PageInfo<Brand> findPage(Integer page, Integer size, Brand brand);


}
