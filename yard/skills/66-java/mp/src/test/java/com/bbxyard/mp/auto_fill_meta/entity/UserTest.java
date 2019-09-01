package com.bbxyard.mp.auto_fill_meta.entity;

import com.bbxyard.mp.auto_fill_meta.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {

    private static final Logger logger = LoggerFactory.getLogger(UserTest.class);

    @Resource
    private UserMapper userMapper;

    @Test
    public void testAutoFillMeta() {
        User user = new User();
        user.setId(null);
        user.setName("Alber");
        user.setAge(99);
        user.setEmail("aa@phy.com");
        userMapper.insert(user);
        System.out.println("Insert New One and write-back id is: " + user.getId());
        User fUser = userMapper.selectById(user.getId());
        System.out.println("Selected User is: " + fUser);
        fUser.setVersion(fUser.getVersion() + 1);
        fUser.setAge(fUser.getAge() + 1);
        userMapper.updateById(fUser);
        User fUserV2 = userMapper.selectById(user.getId());
        System.out.println("Selected User new version is: " + fUserV2);
    }

}