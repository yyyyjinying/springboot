package sOauth.redis;

import com.sOauth.SoauthApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SoauthApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class Test01 {
    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @Test
    public void redistest() {
        System.out.println("同时获取数据");
        redisTemplate.opsForValue().set("bigimg","aa");
        String value = redisTemplate.opsForValue().get("bigimg");
        System.out.println(value);

//        Boolean bigimg = redisTemplate.delete("bigimg");
//        System.out.println(bigimg);
    }
}
