package com.bbxyard.mp.xenum.entity;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bbxyard.mp.xenum.enums.AgeEnum;
import com.bbxyard.mp.xenum.enums.GenderEnum;
import com.bbxyard.mp.xenum.enums.GradeEnum;
import com.bbxyard.mp.xenum.mapper.UserMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {

    @Resource
    private UserMapper userMapper;

    @Test
    public void storeThenCheck() {
        // 插入一个新用户
        User user = new User();
        user.setAge(AgeEnum.TWO);
        user.setGender(GenderEnum.FEMALE);
        user.setGrade(GradeEnum.G9);
        user.setName("Albert");
        user.setEmail("foobar@test.com");
        userMapper.insert(user);

        System.out.println("新用户ID is: " + user.getId());

        // 查询
        User fUserById = userMapper.selectById(user.getId());
        User fUserByS1 = userMapper.selectOne(new QueryWrapper<User>().lambda().eq(User::getId, user.getId()));
        printUser(fUserById, "selectById");
        printUser(fUserByS1, "selectOne");
        Assert.assertEquals("Id Should be same", fUserById.getId(), fUserByS1.getId());
    }

    private void printUser(User u, String prompt) {
        System.out.printf("=== %s ===\n", prompt);
        System.out.println(u);
    }

}