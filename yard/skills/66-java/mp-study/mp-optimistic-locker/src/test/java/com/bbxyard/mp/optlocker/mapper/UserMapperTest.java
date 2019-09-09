package com.bbxyard.mp.optlocker.mapper;

import com.bbxyard.mp.optlocker.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {

    private static final Logger logger = LoggerFactory.getLogger(UserMapperTest.class);

    @Resource
    private UserMapper userMapper;

    @Test
    public void testUpdateByIdSuccess() {
        User user = userMapper.selectById(1L);
        System.out.println("User: " + user);

        user.setAge(user.getAge() + 100);
        user.setVersion(user.getVersion());
        int lastVersion = user.getVersion();
        int updateRes = userMapper.updateById(user);
        Assert.assertEquals("Should update success", 1, updateRes);
        if (updateRes > 0) {
            System.out.println("Update succesfully");
            Assert.assertEquals("Should version = version+1", lastVersion + 1, user.getVersion().intValue());
        } else {
            System.out.println("Update failed due to modified by others");
        }
    }

    @Test
    public void testUpdateByIdFail() {
        User user = userMapper.selectById(2L);
        System.out.println("User 2 is: " + user);

        int lastVersion = user.getVersion();
        int missMatchedVersion = lastVersion - 1;
        user.setVersion(missMatchedVersion);
        user.setAge(user.getAge() + 1000);
        int updateRes = userMapper.updateById(user);
        Assert.assertEquals("Should update success", 0, updateRes);
        if (updateRes > 0) {
            System.out.println("Update succesfully");
            Assert.assertEquals("Should version = version+1", lastVersion + 1, user.getVersion().intValue());
        } else {
            System.out.println("Update failed due to modified by others");
        }
    }
}