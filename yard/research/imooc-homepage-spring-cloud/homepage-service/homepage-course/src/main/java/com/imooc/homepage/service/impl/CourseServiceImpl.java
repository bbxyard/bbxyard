package com.imooc.homepage.service.impl;

import com.imooc.homepage.CourseInfo;
import com.imooc.homepage.CourseInfosRequest;
import com.imooc.homepage.dao.HomepageCourseDao;
import com.imooc.homepage.entity.HomepageCourse;
import com.imooc.homepage.service.ICourseService;
import javafx.print.Collation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CourseServiceImpl implements ICourseService {

    private HomepageCourseDao courseDao;

    @Autowired
    public CourseServiceImpl(HomepageCourseDao courseDao) {
        this.courseDao = courseDao;
    }

    @Override
    public CourseInfo getCourseInfo(Long id) {
        Optional<HomepageCourse> course = courseDao.findById(id);
        return buildCourseInfo(course.orElse(HomepageCourse.invalid()));
    }

    @Override
    public List<CourseInfo> getCourseInfos(CourseInfosRequest request) {
        if (CollectionUtils.isEmpty(request.getIds())) {
            return Collections.emptyList();
        }
        List<HomepageCourse> courses = courseDao.findAllById(request.getIds());
        return courses.stream()
                .map(this::buildCourseInfo)
                .collect(Collectors.toList());
    }

    /**
     * <h2>根据数据记录构造对象信息</h2>
     * @param course
     * @return
     */
    private CourseInfo buildCourseInfo(HomepageCourse course) {
        return CourseInfo.builder()
                .id(course.getId())
                .courseName(course.getCourseName())
                .courseType(course.getCourseType() == 0 ? "免费课程" : "实战课程")
                .courseIcon(course.getCourseIcon())
                .courseIntro(course.getCourseIntro())
                .build();
    }
}
