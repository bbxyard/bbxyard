package com.bbxyard.mp.comb.service.impl;

import com.bbxyard.mp.comb.entity.Student;
import com.bbxyard.mp.comb.mapper.StudentMapper;
import com.bbxyard.mp.comb.service.StudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 学生表 服务实现类
 * </p>
 *
 * @author bbxyard
 * @since 2019-09-27
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

}
