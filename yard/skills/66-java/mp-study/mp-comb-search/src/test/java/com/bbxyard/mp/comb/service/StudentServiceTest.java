package com.bbxyard.mp.comb.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.additional.query.impl.QueryChainWrapper;
import com.bbxyard.mp.comb.entity.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class StudentServiceTest {

    @Resource
    private StudentService studentService;

    @Test
    public void testInsert() {
        int offset = 1000;
        List<Student> studentList = new ArrayList<>();
        for (int i = offset; i < 10 + offset; i++) {
            Student stu = new Student();
            stu.setName("user-" + i);
            stu.setEmail(stu.getName() + "@haha.com");
            stu.setPassword(String.format("**%d**", i));
            stu.setPhone(String.format("135-1234-%d", i));
            stu.setPoint(i * 50);
            stu.setRealName(String.format("name-%d", i));
            stu.setSn(String.format("2019%d", i));
            studentList.add(stu);
        }
        studentService.saveBatch(studentList);
    }

    @Test
    public void testDelete() {
        studentService.remove(new QueryWrapper<Student>().le("id", 10));
    }
}