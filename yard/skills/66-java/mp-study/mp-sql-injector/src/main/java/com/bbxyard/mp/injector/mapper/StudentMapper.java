package com.bbxyard.mp.injector.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bbxyard.mp.injector.entity.Student;
import org.apache.ibatis.annotations.Mapper;


/**
 * 学生Mapper层
 * @author nieqiurong 2018/8/11 20:21.
 */
@Mapper
public interface StudentMapper extends BaseMapper<Student> {

    void deleteAll();

}
