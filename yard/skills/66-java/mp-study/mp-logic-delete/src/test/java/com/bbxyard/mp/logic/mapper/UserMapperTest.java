package com.bbxyard.mp.logic.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bbxyard.mp.logic.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {

    @Resource
    private UserMapper userMapper;

    @Test
    public void testQueryAll() {
        List<User> list = userMapper.selectList(null);
        list.forEach(System.out::println);
    }

    @Test
    public void testLogicDeleteById() {
        userMapper.deleteById(1);
    }

    @Test
    public void testLogicDeleteBatchIds() {
        userMapper.deleteBatchIds(Arrays.asList(4, 2, 3));
    }

    @Test
    public void testLogicDelete() {
        userMapper.delete(new QueryWrapper<User>().eq("age", 2));
    }

}