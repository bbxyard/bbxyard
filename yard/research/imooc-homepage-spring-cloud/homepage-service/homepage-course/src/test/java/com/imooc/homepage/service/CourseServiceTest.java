package com.imooc.homepage.service;

import com.alibaba.fastjson.JSON;
import com.imooc.homepage.CourseInfo;
import com.imooc.homepage.CourseInfosRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

/**
 * <h1>课程服务测试</h1>
 * Created by Qinyi.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CourseServiceTest {

    @Autowired
    private ICourseService courseService;

    @Test
    public void getCourseInfo() {
        System.out.println(courseService.getCourseInfo(8L));
        System.out.println(JSON.toJSONString(courseService.getCourseInfo(9L)));
    }

    @Test
    public void getCourseInfos() {
        List<CourseInfo> courseInfos = courseService.getCourseInfos(new CourseInfosRequest(Arrays.asList(7L, 8L, 9L)));
        System.out.println(JSON.toJSONString(courseInfos));
    }
}