package com.changgou.goods.controller;

import com.changgou.goods.pojo.Rimg;
import com.changgou.goods.service.RimgService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rimg")
@CrossOrigin
public class RimgController {
    @Autowired
    RimgService rimgService;


    @GetMapping
    public Result<List<Rimg>> selectAll(){
        List<Rimg> rimgs = rimgService.selectAll();
        return new Result<List<Rimg>>(true,StatusCode.OK,"请求成功",rimgs);
    }

    @DeleteMapping("/{id}")
    public Result<Integer> del(@PathVariable(value = "id") Integer id){
        Integer index = rimgService.del(id);
        return new Result<Integer>(true,StatusCode.OK,"删除成功",index);
    }

}
