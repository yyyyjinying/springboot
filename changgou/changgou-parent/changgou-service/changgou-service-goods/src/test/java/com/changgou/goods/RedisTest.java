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
        Object a = redisTemplate.opsForValue().get("bigimg");
        System.out.println(a);

        Boolean bigimg = redisTemplate.delete("bigimg");
        System.out.println(bigimg);

    }


}
