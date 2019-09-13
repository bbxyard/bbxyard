package com.bbxyard.mp.injector.entity;

import com.bbxyard.mp.injector.mapper.StudentMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class StudentTest {

    @Resource
    private StudentMapper mapper;

    @Test
    public void test() {
        mapper.deleteAll();
    }

}
