package com.imooc.homepage.service;

import com.alibaba.fastjson.JSON;
import com.imooc.homepage.UserInfo;
import com.imooc.homepage.vo.CreateUserRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IUserServiceTest {

    @Resource
    private IUserService userService;

    @Test
    public void createUser() {
        UserInfo userInfo = userService.createUser(new CreateUserRequest("Albert", "albert@imooc.com"));
        System.out.println(JSON.toJSONString(userInfo));
    }

    @Test
    public void getUserInfo() {
    }

    @Test
    public void getUserCourseInfo() {
    }
}