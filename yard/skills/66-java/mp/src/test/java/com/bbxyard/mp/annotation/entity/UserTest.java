package com.bbxyard.mp.annotation.entity;

import com.bbxyard.mp.annotation.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {

    @Resource
    private UserMapper userMapper;


    @Test
    public void testSelectAll() {
        List<User> userList = userMapper.selectList(null);
        userList.forEach(System.out::println);
    }
}