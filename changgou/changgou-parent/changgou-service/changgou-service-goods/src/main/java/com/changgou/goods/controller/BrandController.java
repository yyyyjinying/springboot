package com.changgou.goods.controller;


import com.changgou.goods.pojo.Brand;
import com.changgou.goods.service.BrandService;
import com.github.pagehelper.PageInfo;
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

    @PostMapping(value = "/search")
    public Result<List<Brand>> findList(@RequestBody  Brand brand){

        List<Brand> list = brandService.findList(brand);
        return new Result(true, StatusCode.OK,"简单条件查询", list);

    }

    @GetMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo<Brand>> findPage(@PathVariable  Integer page, @PathVariable  Integer size){
        //分页查询
        PageInfo<Brand> pageInfo = brandService.findPage(page, size);
        return new Result<PageInfo<Brand>>(true,StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * 分页搜索实现
     * @param brand
     * @param page
     * @param size
     * @return
     */
    @PostMapping(value = "/search/{page}/{size}")
    public Result<PageInfo> findPage(@RequestBody(required = false) Brand brand, @PathVariable  int page, @PathVariable  int size){
        //执行搜索
        PageInfo<Brand> pageInfo = brandService.findPage(page, size, brand);
//        int i = 10/0;
        return new Result(true,StatusCode.OK,"查询成功",pageInfo);
    }
}
