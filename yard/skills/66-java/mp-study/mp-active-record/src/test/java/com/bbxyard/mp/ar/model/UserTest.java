package com.bbxyard.mp.ar.model;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserTest {

    @Test
    public void testInsert() {
        User user = new User();
        user.setName("秦始皇");
        user.setAge(999);
        user.setEmail("qsh@king.com");
        log.info("Insert id: ", user.insert());
    }

    @Test
    public void testDelete() {
        assertTrue(new User().setId(3L).deleteById());
        assertTrue(new User().delete(new QueryWrapper<User>().lambda().eq(User::getName, "Sandy")));
    }

    @Test
    public void testUpdate() {
        assertTrue(new User().setId(1L).setEmail("one@test.com").updateById());
        assertTrue(new User().update(new UpdateWrapper<User>().lambda().set(User::getAge, 1024).eq(User::getId, 2L)));
    }

    @Test
    public void testSelect() {
        assertEquals("one@test.com", new User().setId(1L).selectById().getEmail());
        User user = new User().selectOne(new QueryWrapper<User>().lambda().eq(User::getAge, 1024));
        assertEquals("Jack", user.getName());
        assertTrue(2L == user.getId());
    }
}
