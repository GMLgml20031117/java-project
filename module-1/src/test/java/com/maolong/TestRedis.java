package com.maolong;

import com.maolong.common.consitant.ResultConstant;
import com.maolong.common.util.JsonObjUtil;
import com.maolong.pojo.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

@SpringBootTest
public class TestRedis {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testRedis() throws Exception {
        redisTemplate.opsForValue().set("test","hello", 10000, TimeUnit.MILLISECONDS);
        String test = redisTemplate.opsForValue().get("test").toString();
        System.out.println(test);


        Object o = redisTemplate.opsForValue().get(ResultConstant.REDIS_TOKEN_KEY);
        System.out.println(o);

//        User user = JsonObjUtil.toObject(test, User.class);

    }

}
