package com.imooc.controller;

import com.bbxyard.spboot.dto.HttpRespMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Map;


@RestController
@RequestMapping("redis")
public class RedisController {

    @Autowired
    private StringRedisTemplate strRedis;

    @RequestMapping("/test_raw_jedis")
    public HttpRespMsg testRedisPool() {
        JedisPool jedisPool = new JedisPool("localhost", 6379);
        Jedis jedis = jedisPool.getResource();
        final String key = "study:java:springboot:user";
        jedis.hset(key, "from", "imooc");
        jedis.hset(key, "course", "SpringBoot开发常用技术整合");
        jedis.hset(key, "teacher", "风间影月");
        jedis.hset(key, "url", "https://www.imooc.com/t/3078817");
        Map<String, String> kvMap = jedis.hgetAll(key);
        return HttpRespMsg.Ok(kvMap);
    }

    @RequestMapping("/string_set_get")
    public HttpRespMsg testStringSetGet() {
        System.out.println(strRedis);

        strRedis.opsForValue().set("study:java:springboot:url", "https://www.imooc.com/learn/956");
        strRedis.opsForValue().increment("study:java:springboot:times");
        String courseUrl = (String)strRedis.opsForValue().get("study:java:springboot:url");

        return HttpRespMsg.Ok(courseUrl);
    }
}
