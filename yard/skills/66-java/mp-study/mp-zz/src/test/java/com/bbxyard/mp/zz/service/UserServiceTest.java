package com.bbxyard.mp.zz.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.bbxyard.mp.zz.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTest {

    @Resource
    private UserService userService;

    @Test
    public void testStore() {
        User user = new User();
        user.setName("孔子");
        user.setAge(999);
        user.setEmail("kz@vip.com");

        User user2 = new User();
        user2.setName("孟子");
        user2.setAge(888);
        user2.setEmail("mz@vip.com");
        userService.saveOrUpdateBatch(Arrays.asList(user, user2));
    }

    @Test
    public void testListAll() {
        List<User> list = userService.list();
        for (User user : list) {
            System.out.println(user);
        }
    }

    @Test
    public void testGetOne() {
        User user = userService.getOne(Wrappers.<User>lambdaQuery().eq(User::getId, 3L));
        System.out.println(user);
    }

    @Test
    public void testQuery() {
        List<User> users = userService.lambdaQuery().gt(User::getAge, 100).like(User::getName, "子").list();
        System.out.println(users);
    }

    @Test
    public void testChainUpdate() {
        boolean updated1 = userService.lambdaUpdate().lt(User::getAge, 100).set(User::getVersion, 3).update();
        boolean updated2 = userService.lambdaUpdate().gt(User::getAge, 100).set(User::getVersion, 7).update();
        System.out.println("Updated: " + Arrays.asList(updated1, updated2));
    }
}
