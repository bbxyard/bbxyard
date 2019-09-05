package com.imooc.homepage.dao;

import com.imooc.homepage.entity.HomepageUserCourse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * <h1>HomepageUserCourse 数据表访问接口定义</h1>
 * Created by Qinyi.
 */
public interface HomepageUserCourseDao extends JpaRepository<HomepageUserCourse, Long> {

    /**
     * <h2>通过用户 id 寻找数据记录</h2>
     * */
    List<HomepageUserCourse> findAllByUserId(Long userId);

}
