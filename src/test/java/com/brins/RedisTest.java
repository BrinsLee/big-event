package com.brins;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.concurrent.TimeUnit;

/**
 * Created by lipeilin on 2024/10/21.
 */
// 如果测试类添加此注解，将来单元测试方法执行之前会初始化Spring容器
@SpringBootTest
public class RedisTest {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void testSet(){
        // 存储键值对
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        operations.set("username","admin");
        operations.set("id", "1", 15, TimeUnit.SECONDS);
    }

    @Test
    public void testGet(){
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        String username = operations.get("username");
        System.out.println(username);
    }
}
