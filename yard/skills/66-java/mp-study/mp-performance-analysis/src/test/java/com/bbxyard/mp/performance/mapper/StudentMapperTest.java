package com.bbxyard.mp.performance.mapper;

import com.bbxyard.mp.performance.entity.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.awt.*;
import java.util.List;


@SpringBootTest
@RunWith(SpringRunner.class)
public class StudentMapperTest {

    @Resource
    private StudentMapper mapper;

    @Test
    public void testStore() {
        List<Student> list = mapper.selectList(null);
        list.forEach(System.out::println);
    }

}