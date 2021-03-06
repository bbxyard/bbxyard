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
    public void testGet() {
        User user = userMapper.selectById(2L);
        System.out.println(user);
    }

    @Test
    public void testAutoFillMeta() {
        User user = new User();
        user.setId(null);
        user.setName("古斯塔夫");
        user.setAge(99);
        user.setEmail("aa@phy.com");
        user.setRemark("现在插入一条");
        userMapper.insert(user);
        System.out.println("Insert New One and write-back id is: " + user.getId());

        // 第一次查询+更新
        User fUser = userMapper.selectById(user.getId());
        System.out.println("Selected User is: " + fUser);
        fUser.setVersion(fUser.getVersion() + 1);
        fUser.setAge(fUser.getAge() + 1);
        userMapper.updateById(fUser);
        User fUserV2 = userMapper.selectById(user.getId());
        System.out.println("Selected User 2nd version is: " + fUserV2);

        // 第二次查询+更新
        fUser.setEmail("gstf@war.com");
        userMapper.updateById(fUser);
        System.out.println("Selected User 3rd version is: " + fUserV2);
    }
}
