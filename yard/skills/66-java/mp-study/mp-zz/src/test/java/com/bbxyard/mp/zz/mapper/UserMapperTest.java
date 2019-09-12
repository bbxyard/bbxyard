package com.bbxyard.mp.zz.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.bbxyard.mp.zz.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.sql.Wrapper;
import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserMapperTest {

    @Resource
    private UserMapper userMapper;

    @Test
    public void listAllByCustom() {

        LambdaQueryWrapper<User> lambdaQueryWrapper = Wrappers.<User>lambdaQuery();
        lambdaQueryWrapper.likeLeft(User::getName,"子").or().isNotNull(User::getEmail);
        List<User> users = userMapper.listAllByCustom(lambdaQueryWrapper);
        for (User user : users) {
            System.out.println(user);
        }

    }

    @Test
    public void listAllByCustomFromXml() {
        LambdaQueryWrapper<User> lambdaQueryWrapper = Wrappers.<User>lambdaQuery();
        lambdaQueryWrapper.likeLeft(User::getName,"子");
        List<User> users = userMapper.listAllByCustomFromXml(lambdaQueryWrapper);
        for (User user : users) {
            System.out.println(user);
        }
    }
}
