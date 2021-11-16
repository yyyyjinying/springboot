package com.changgou.order.controller;

import com.changgou.order.pojo.OrderItem;
import com.changgou.order.service.CartService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/cart")
public class CartController {

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    private CartService cartService;

    /***
     * 加入购物车
     * @param num:购买的数量
     * @param id：购买的商品(SKU)ID
     * @return
     * 10000005620800
     * http://localhost:18090/cart/add?num=6&id=10000005620800
     */
    @RequestMapping(value = "/add")
    public Result add(Integer num, String id){
        //用户名
        String username="szitheima";
        //将商品加入购物车
        cartService.add(num,id,username);
        return new Result(true, StatusCode.OK,"加入购物车成功！");
    }

    @GetMapping("/list")
    public Result<List<OrderItem>> list(){
//用户名
        String username="szitheima";
        List<OrderItem> list = cartService.list(username);
        return new Result<List<OrderItem>>(true,StatusCode.OK, "请求成功",list);
    }



}
