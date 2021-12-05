package com.changgou.order.controller;

import com.changgou.goods.pojo.Brand;
import com.changgou.order.pojo.OrderItem;
import com.changgou.order.service.CartService;
import com.changgou.order.utils.TokenUtils;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping(value = "/cart")
public class CartController {

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    private CartService cartService;

    /**
     * 测试feign
     */
    @GetMapping("/brand/{id}")
    public Result<Brand> findBrandById(@PathVariable("id") Integer id){
        Brand brandById = cartService.findBrandById(id);
        return new Result<Brand>(true, StatusCode.OK, "测试feign",brandById);
    }

    /***
     * 加入购物车
     * @param num:购买的数量
     * @param id：购买的商品(SKU)ID
     * @return
     * 10000005620800
     * http://localhost:18090/cart/add?num=6&id=100000003145
     */
    @RequestMapping(value = "/add")
    public Result add(Integer num, String id){
        //用户名
//        String username="szitheima";
        Map<String, String> userInfo = TokenUtils.getUserInfo();
        String username = userInfo.get("user_name");
        //将商品加入购物车
        cartService.add(num,id,username);
        return new Result(true, StatusCode.OK,"加入购物车成功！");
    }

    @GetMapping("/list")
    @PreAuthorize("hasAnyAuthority('p1')")
    public Result<List<OrderItem>> list(){
//用户名
//        String username="szitheima";
        Map<String, String> userInfo = TokenUtils.getUserInfo();
        String username = userInfo.get("user_name");
        List<OrderItem> list = cartService.list(username);
        return new Result<List<OrderItem>>(true,StatusCode.OK, "请求成功",list);
    }



}
