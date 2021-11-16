package com.changgou.order.service.impl;
import com.changgou.goods.feign.SkuFeign;
import com.changgou.goods.feign.SpuFeign;
import com.changgou.goods.pojo.Sku;
import com.changgou.goods.pojo.Spu;
import com.changgou.order.pojo.OrderItem;
import com.changgou.order.service.CartService;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private SkuFeign skuFeign;

    @Autowired
    private SpuFeign spuFeign;

    @Override
    public List<OrderItem> list(String username) {
        List<OrderItem> list = redisTemplate.boundHashOps("Cart_" + username).values();
        return list;
    }

    /**
     * 加入购物车
     * @param num 添加的数量
     * @param id 添加的商品ID
     * @param username 购买用户
     */
    @Override
    public void add(Integer num, String id, String username) {

        // 设置OrderItem

        // 1.获取对应的fe分类
        Result<Sku> resultSku = skuFeign.getId(id);
        Sku sku = resultSku.getData();

        Result<Spu> resultSpu = spuFeign.getId(sku.getSpuId());
        Spu spu = resultSpu.getData();


        OrderItem orderItem = new OrderItem();
        orderItem.setCategoryId1(spu.getCategory1Id());
        orderItem.setCategoryId2(spu.getCategory2Id());
        orderItem.setCategoryId3(spu.getCategory3Id());

        orderItem.setSpuId(sku.getSpuId().toString());
        orderItem.setSkuId(sku.getId().toString());

        orderItem.setName(sku.getName());
        orderItem.setPrice(sku.getPrice());
        orderItem.setNum(num);

        orderItem.setMoney(num*orderItem.getPrice());       //单价*数量
        orderItem.setPayMoney(num*orderItem.getPrice());    //实付金额
        orderItem.setImage(sku.getImage());
        orderItem.setWeight(sku.getWeight()*num);           //重量=单个重量*数量

        /******
         * 购物车数据存入到Redis
         * namespace = Cart_[username]
         * key=id(sku)
         * value=OrderItem
         */
        redisTemplate.boundHashOps("Cart_"+username).put(id,orderItem);



    }
}
