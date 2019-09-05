package com.imooc.homepage.client;

import com.imooc.homepage.CourseInfo;
import com.imooc.homepage.CourseInfoRequest;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

/**
 * <h1>熔断降级策略</h1>
 * Created by Qinyi.
 */
@Component
public class CourseClientHystrix implements CourseClient {

    @Override
    public CourseInfo getCourseInfo(Long id) {
        return CourseInfo.invalid();
    }

    @Override
    public List<CourseInfo> getCourseInfos(CourseInfoRequest request) {
        return Collections.emptyList();
    }
}
