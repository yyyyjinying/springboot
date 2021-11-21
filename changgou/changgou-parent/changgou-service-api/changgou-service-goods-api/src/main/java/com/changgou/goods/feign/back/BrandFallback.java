package com.changgou.goods.feign.back;

import com.changgou.goods.feign.BrandFeign;
import com.changgou.goods.pojo.Brand;
import entity.Result;
import entity.StatusCode;
import org.springframework.stereotype.Component;



public class BrandFallback implements BrandFeign {
    @Override
    public Result<Brand> findBrandById(Integer id) {
        Brand brand = new Brand();
        brand.setName("aaaaa");
        return new Result(true, StatusCode.OK, "fegin请求超时",brand);
    }
}
