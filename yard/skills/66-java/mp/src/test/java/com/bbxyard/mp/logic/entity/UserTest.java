package com.bbxyard.mp.logic.entity;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bbxyard.mp.logic.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {

    @Resource
    private UserMapper userMapper;

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
