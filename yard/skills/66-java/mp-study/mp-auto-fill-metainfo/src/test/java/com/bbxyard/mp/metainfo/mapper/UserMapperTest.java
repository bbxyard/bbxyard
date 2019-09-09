package com.bbxyard.mp.metainfo.mapper;

import com.bbxyard.mp.metainfo.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class UserMapperTest {

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
