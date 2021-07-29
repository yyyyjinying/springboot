package com.changgou.goods.dao;
import com.changgou.goods.pojo.Brand;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/****
 * @Author:admin
 * @Description:Brand的Dao
 * @Date 2019/6/14 0:12
 *****/
public interface BrandMapper extends Mapper<Brand> {
    /**
     * 根据分类ID获取商品信息
     * @param categoryId
     * @return
     */
    @Select("select b.* from tb_brand b, tb_category_brand c where b.id = c.brand_id and c.category_id=#{categoryId}")
    List<Brand> getBrandByCategory(Integer categoryId);
}
