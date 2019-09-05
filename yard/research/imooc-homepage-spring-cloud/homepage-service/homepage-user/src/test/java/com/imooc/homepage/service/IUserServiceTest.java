package com.imooc.homepage.service;

import com.alibaba.fastjson.JSON;
import com.imooc.homepage.UserApplicationTest;
import com.imooc.homepage.UserInfo;
import com.imooc.homepage.dao.HomepageUserCourseDao;
import com.imooc.homepage.entity.HomepageUser;
import com.imooc.homepage.entity.HomepageUserCourse;
import com.imooc.homepage.vo.CreateUserRequest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {UserApplicationTest.class})
public class IUserServiceTest {

    @Autowired
    private IUserService userService;

    @Autowired
    private HomepageUserCourseDao userCourseDao;

    @Test
    public void createUser() {
        UserInfo userInfo1 = userService.createUser(new CreateUserRequest("Albert", "albert@imooc.com"));
        UserInfo userInfo2 = userService.createUser(new CreateUserRequest("Newton", "newton@imooc.com"));
        UserInfo userInfo3 = userService.createUser(new CreateUserRequest("HLG", "hlg@imooc.com"));
        UserInfo userInfo4 = userService.createUser(new CreateUserRequest("Gauss", "gauss@imooc.com"));
        UserInfo userInfo5 = userService.createUser(new CreateUserRequest("ZCZ", "zcz@imooc.com"));
        System.out.println(JSON.toJSONString(Arrays.asList(userInfo1, userInfo2, userInfo3, userInfo4, userInfo5)));
    }

    @Test
    public void getUserInfo() {
        UserInfo userInfo = userService.getUserInfo(9L);
        System.out.println(JSON.toJSONString(userInfo));
    }

    @Before
    public void beforeAction() {
        System.out.println("User Service Test Begin");
    }

    @Test
    public void getUserCourseInfo() {
        List<HomepageUserCourse> outList = userCourseDao.saveAll(this.getTestCourseList());
        System.out.println(JSON.toJSONString(outList));

    }

    @After
    public void afterAction() {
        List<HomepageUserCourse> inList = this.getTestCourseList();
        for (HomepageUserCourse userCourse : inList) {
            userCourseDao.delete(userCourse);
        }
        System.out.println("User Service Test Done");
    }

    private List<HomepageUserCourse> getTestCourseList() {
        HomepageUserCourse course1 = new HomepageUserCourse();
        course1.setUserId(11L);
        course1.setCourseId(6L);

        HomepageUserCourse course2 = new HomepageUserCourse();
        course2.setUserId(12L);
        course2.setCourseId(7L);

        return Arrays.asList(course1, course2);
    }
}