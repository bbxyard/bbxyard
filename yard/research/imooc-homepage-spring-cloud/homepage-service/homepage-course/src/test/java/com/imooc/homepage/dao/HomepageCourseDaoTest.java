package com.imooc.homepage.dao;

import com.imooc.homepage.entity.HomepageCourse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class HomepageCourseDaoTest {

    @Autowired
    private HomepageCourseDao courseDao;

    @Test
    public void testCreateCourseInfo() {
        HomepageCourse course1 = new HomepageCourse(
                "JDK11&12 新特性解读",
                0,
                "https://www.imooc.com",
                "解读 JDK11 和 JDK12 的新版本特性"
        );
        HomepageCourse course2 = new HomepageCourse(
                "基于 SpringCloud 微服务架构 广告系统设计与实现",
                1,
                "https://www.imooc.com",
                "广告系统的设计与实现"
        );
        System.out.println(courseDao.saveAll(
                Arrays.asList(course1, course2)
        ).size());

    }
}