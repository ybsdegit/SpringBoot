package com.ybs;

import com.ybs.utils.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class SpringRedisApplicationTests {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    void contextLoads() {
    }

    @Test
    void test() {
        // opsForValue 操作字符串
        // opsForList 操作List
        // opsForSet 操作集合
        // opsForHash
        // opsForGeo 地图
        // opsForZSet

        // 获取连接对象
        // RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();
        // connection.flushAll();

        redisTemplate.opsForValue().set("ybs", "Paulson");
        System.out.println(redisTemplate.opsForValue().get("ybs"));
    }


    @Autowired
    private RedisUtil redisUtil;

    @Test
    void testRedisUtil(){
        redisUtil.set("name", "元宝森");
        System.out.println(redisUtil.get("name"));
    }
}
