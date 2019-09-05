package com.imooc.homepage.service;

import com.alibaba.fastjson.JSON;
import com.imooc.homepage.UserApplicationTest;
import com.imooc.homepage.UserInfo;
import com.imooc.homepage.vo.CreateUserRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {UserApplicationTest.class})
public class IUserServiceTest {

    @Autowired
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