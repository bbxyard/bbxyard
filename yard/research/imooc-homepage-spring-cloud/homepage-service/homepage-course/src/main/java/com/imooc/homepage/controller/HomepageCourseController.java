package com.imooc.homepage.controller;

import com.alibaba.fastjson.JSON;
import com.imooc.homepage.CourseInfo;
import com.imooc.homepage.CourseInfosRequest;
import com.imooc.homepage.service.ICourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <h1>课程对外服务接口</h1>
 */
@Slf4j
@RestController
public class HomepageCourseController {

    private final ICourseService courseService;

    @Autowired
    public HomepageCourseController(ICourseService courseService) {
        this.courseService = courseService;
    }

    /**
     *
     * 127.0.0.1:7001/homepage-course/get/course?id=
     * 127.0.0.1:9000/imooc/homepage-course/get/course?id=
     *
     * @param id
     * @return
     */
    @GetMapping("/get/course")
    public CourseInfo getCourseInfo(Long id) {
        log.info("<homepage-course>: get course -> {}", id);
        return courseService.getCourseInfo(id);
    }

    @PostMapping("/get/courses")
    public List<CourseInfo> getCourseInfos(@RequestBody CourseInfosRequest request) {
        log.info("<homepage-course>: get courses -> {}", JSON.toJSONString(request));
        return courseService.getCourseInfos(request);
    }
}
