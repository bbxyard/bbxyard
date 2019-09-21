package com.bbxyard.sfb.redis.entity;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;


@SpringBootTest
@RunWith(SpringRunner.class)
public class UserTest {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private RedisTemplate redisTemplate;


    @Test
    public void test() throws Exception {
        stringRedisTemplate.opsForValue().set("hallo", "Deutschland");
        Assert.assertEquals("Deutschland", stringRedisTemplate.opsForValue().get("hallo"));
    }

    @Test
    public void testObject() throws Exception {
        User user = new User();
        user.setEmail("haha@126.com");
        user.setUserName("肯特");
        user.setNickName("超人");
        user.setPassword("***123***");
        ValueOperations<String, User> operations = redisTemplate.opsForValue();
        operations.set("com.neox", user);
        operations.set("com.neo.f", user, 1, TimeUnit.SECONDS);
        Thread.sleep(1000);
        boolean exists = redisTemplate.hasKey("com.neo.f");
        if (exists) {
            System.out.println("exists is true");
        } else {
            System.out.println("exists is false");
        }
    }
}