package com.changgou.goods;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {


    @Autowired
    RedisTemplate<String, String> redisTemplate;


    @Test
    public void redistest() {
        System.out.println("同时获取数据");

//        RedisTemplate redisTemplate = new RedisTemplate();
//
        Object a = redisTemplate.opsForValue().get("a");
        System.out.println(a);


//        RedisTemplate<Object, Object> objectObjectRedisTemplate = new RedisTemplate<>();
//        List<RedisClientInfo> clientList = objectObjectRedisTemplate.getClientList();
//        RedisClientInfo a = clientList.get(Integer.parseInt("a"));
//        System.out.println(a);

    }


}
