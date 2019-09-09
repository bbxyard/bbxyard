package com.bbxyard.mp.quickstart.mapper;

import com.bbxyard.mp.quickstart.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {

    @Resource
    private UserMapper userMapper;

    @Test
    public void tesQuery() {
        List<User> list = userMapper.selectList(null);
        for (User user : list) {
            System.out.println(user);
        }
        list.forEach(System.out::println);
    }

}
