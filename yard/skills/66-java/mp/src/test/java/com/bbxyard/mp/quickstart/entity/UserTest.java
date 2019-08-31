package com.bbxyard.mp.quickstart.entity;

import com.bbxyard.mp.quickstart.mapper.UserMapper;
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
    public void testQuery() {
        System.out.println("----- SelectAll method Test -----");
        List<User> userList = userMapper.selectList(null);
        for (User user : userList) {
            System.out.printf("user: %s %s %d %d\n", user.getName(), user.getEmail(), user.getId(), user.getAge());
        }
    }

}
