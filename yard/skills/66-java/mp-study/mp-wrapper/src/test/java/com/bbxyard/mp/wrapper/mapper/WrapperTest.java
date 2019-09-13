package com.bbxyard.mp.wrapper.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.bbxyard.mp.wrapper.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class WrapperTest {

    @Resource
    private UserMapper userMapper;

    @Resource
    private RoleMapper roleMapper;


    @Test
    public void test1() {
        System.out.println("--- 普通查询 ---");
        List<User> plainUsers = userMapper.selectList(new QueryWrapper<User>().eq("role_id", 2L));
        List<User> lambdaUsers = userMapper.selectList(new QueryWrapper<User>().lambda().eq(User::getRoleId, 2L));
        assertEquals(plainUsers.size(), lambdaUsers.size());
        display(plainUsers);

        System.out.println("--- 带子查询(sql注入) ---");
        List<User> plainUsers2 = userMapper.selectList(new QueryWrapper<User>()
            .inSql("role_Id", "select id from mp_wrapper_role where id = 2"));
        List<User> lambdaUsers2 = userMapper.selectList(new QueryWrapper<User>().lambda()
            .inSql(User::getRoleId, "select id from mp_wrapper_role where id = 2"));
        assertEquals(plainUsers2.size(), lambdaUsers2.size());
        display(plainUsers2);

        System.out.println("--- 带子查询(sql注入) ---");
        List<User> plainUsers3 = userMapper.selectList(new QueryWrapper<User>()
            .nested(i -> i.eq("role_id", 2L).or().eq("role_id", 3L))
            .and(i -> i.ge("age", 20)));
        List<User> lambdaUsers3 = userMapper.selectList(new QueryWrapper<User>().lambda()
            .nested(i -> i.eq(User::getRoleId, 2L).or().eq(User::getRoleId, 3L))
            .and(i -> i.ge(User::getAge, 20)));
        assertEquals(plainUsers3.size(), lambdaUsers3.size());
        display(plainUsers3);

        System.out.println("--- 自定义(sql注入) ---");
        List<User> plainUsers4 = userMapper.selectList(new QueryWrapper<User>().apply("role_id = 2"));
        display(plainUsers4);

        System.out.println("--- 更新 ---");
        UpdateWrapper<User> uw = new UpdateWrapper<>();
        uw.set("email", null);
        uw.eq("id", 4L);
        userMapper.update(new User(), uw);
        User u4 = userMapper.selectById(4L);
        assertEquals(u4.getEmail(), null);
    }

    private <T> void display(List<T> list) {
        if (!CollectionUtils.isEmpty(list)) {
            list.forEach(System.out::println);
        }
    }

    /**
     * SELECT id,name,age,email,role_id FROM user
     * WHERE ( 1 = 1 ) AND ( ( name = ? AND age = ? ) OR ( name = ? AND age = ? ) )
     */
    @Test
    public void testSql() {
        QueryWrapper<User> w = new QueryWrapper<>();
        w.and(i -> i.eq("1", 1))
                .nested(i->
                        i.and(j -> j.eq("name", "a").eq("age", 2))
                            .or(j -> j.eq("name", "b").eq("age", 2)));
    }
}