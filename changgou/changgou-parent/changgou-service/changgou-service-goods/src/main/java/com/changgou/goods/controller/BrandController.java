package com.changgou.goods.controller;


import com.changgou.goods.pojo.Brand;
import com.changgou.goods.service.BrandService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/brand")
public class BrandController {

   @Autowired
   BrandService brandService;

    @GetMapping
    public Result<List<Brand>> findAll(){
        List<Brand> all = brandService.findAll();
        return new Result<List<Brand>>(true, StatusCode.OK, "查询成功", all);
    }

    @GetMapping("/{id}")
    public Result<Brand> findById(@PathVariable(name="id") Integer id){
        Brand brand = brandService.findById(id);
        return new Result<Brand>(true,StatusCode.OK,"查询品牌成功", brand);
    }

    @PostMapping
    public Result add(@RequestBody Brand brand){
        brandService.add(brand);
        return new Result(true, StatusCode.OK, "添加成功");
    }

    @PutMapping("/{id}")
    public Result update(@PathVariable(name = "id") Integer id, @RequestBody Brand brand){
        brand.setId(id);
        brandService.update(brand);
        return new Result(true, StatusCode.OK,"修改成功");
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable(name="id") Integer id){
        brandService.delete(id);
        return new Result(true,StatusCode.OK,"删除成功");

    }
}
