package com.bbxyard.mp.xenum.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bbxyard.mp.xenum.entity.User;
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

import static org.junit.Assert.*;


@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTest {

    @Resource
    private UserMapper userMapper;

    @Test
    public void getAll() {
    }

    @Test
    public void getById() {
    }

    @Test
    public void deleteById() {
    }

    @Test
    public void save() {
    }

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

        // 更新
        Assert.assertTrue(userMapper.update(new User().setAge(AgeEnum.THREE),
                new QueryWrapper<User>().eq("age", AgeEnum.TWO)) > 0);

        // 删除
        Assert.assertTrue(userMapper.delete(new QueryWrapper<User>().
                lambda().eq(User::getGrade, GradeEnum.G9)) > 0);

        Assert.assertEquals("Id Should be same", fUserById.getId(), fUserByS1.getId());
    }

    private void printUser(User u, String prompt) {
        System.out.printf("=== %s ===\n", prompt);
        System.out.println(u);
    }
}