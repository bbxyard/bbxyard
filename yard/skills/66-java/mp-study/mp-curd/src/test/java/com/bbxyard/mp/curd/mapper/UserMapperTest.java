package com.bbxyard.mp.curd.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bbxyard.mp.curd.entity.User;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;


@SpringBootTest
@RunWith(SpringRunner.class)
public class UserMapperTest {

    @Resource
    private UserMapper mapper;

    @Test
    public void testStore() {
        User user = new User();
        user.setName("岳飞");
        user.setAge(39);
        user.setEmail("yf@hero.com");
        assertThat(mapper.insert(user), greaterThan(0));
    }

    @Test
    public void test02Delete() {
        assertThat(mapper.deleteById(3L), greaterThanOrEqualTo(0));
        assertThat(mapper.delete(new QueryWrapper<User>().lambda().eq(User::getName, "Sandy")), greaterThanOrEqualTo(0));
    }

    @Test
    public void testOrderBy() {
        List<User> users = mapper.selectList(new QueryWrapper<User>().orderByDesc("age"));
        assertTrue("列表不为空", users.size() > 0);
    }

    @Test
    public void testSelectMaps() {
        List<Map<String, Object>> mapList = mapper.selectMaps(Wrappers.<User>query().orderByAsc("age"));
        assertTrue(!mapList.isEmpty());
        assertTrue(!mapList.get(0).isEmpty());
        System.out.println(mapList.get(0));
    }

    @Test
    public void testSelectMapsPage() {
        IPage<Map<String, Object>> res = mapper.selectMapsPage(new Page<>(1, 5), Wrappers.<User>query().orderByDesc("age"));
        assertTrue(res != null);
        assertTrue(res.getRecords() != null);
        assertTrue(res.getRecords().get(0) != null);
        System.out.println(res.getRecords().get(0));
    }

    @Test
    public void testOrderByLambda() {
        List<User> users = mapper.selectList(Wrappers.<User>lambdaQuery().orderByDesc(User::getAge));
        assertTrue("年龄倒序", !users.isEmpty());
    }

    @Test
    public void testSelectMaxId() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.select("max(id) as id");
        User user = mapper.selectOne(wrapper);
        System.out.println("maxId is: " + user.getId());
        List<User> users = mapper.selectList(Wrappers.<User>lambdaQuery().orderByDesc(User::getId));
        Assert.assertEquals("最大id位于top1", users.get(0).getId(), user.getId());
    }

    @Test
    public void testGroup() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.select("age, count(*)").groupBy("age");
        List<Map<String, Object>> mapList = mapper.selectMaps(wrapper);
        for (Map<String, Object> mp : mapList) {
            System.out.println(mp);
        }
        /**
         * lambdaQueryWrapper OrderBy GroupBy
         */
        LambdaQueryWrapper<User> lambdaQueryWrapper = new QueryWrapper<User>().lambda()
            .select(User::getAge)
            .groupBy(User::getAge)
            .orderByDesc(User::getAge);
        List<User> users = mapper.selectList(lambdaQueryWrapper);
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testTableFieldExistFalse() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.select("age, count(age) as count").groupBy("age");
        List<User> users = mapper.selectList(wrapper);
        for (User user : users) {
            System.out.println(user);
        }
        users.forEach(x -> {
            Assert.assertNull(x.getId());
            Assert.assertNotNull(x.getAge());
            Assert.assertNotNull(x.getCount());
        });

        mapper.insert(
                new User().setId(10088L)
                        .setName("miemie")
                        .setEmail("mm@haha.com")
                        .setAge(3)
        );
        User mm = mapper.selectById(10088L);
        Assert.assertNotNull(mm);
    }

    /**
     * advanced / complex
     */
    @Test
    public void testUpdate() {
        assertThat(mapper.updateById(new User().setId(1L).setEmail("one@one.com")), greaterThan(0));
        assertThat(
                mapper.update(
                        new User().setName("mp"),
                        Wrappers.<User>lambdaUpdate().set(User::getAge, 3).eq(User::getId, 2)
                ), greaterThan(0)
        );

        User user = mapper.selectById(2);
        assertEquals(user.getAge().intValue(), 3);
        assertEquals(user.getName(), "mp");

        mapper.update(
                null,
                Wrappers.<User>lambdaUpdate().set(User::getEmail, null).eq(User::getId, 2)
        );
        assertEquals(mapper.selectById(1).getEmail(), "one@one.com");
        user = mapper.selectById(2);
        assertTrue(user.getEmail() == null);
        assertTrue(user.getName().equals("mp"));

        mapper.update(
                new User().setEmail("miemie@baomidou.com"),
                new QueryWrapper<User>()
                        .lambda().eq(User::getId, 2)
        );
        user = mapper.selectById(2);
        assertEquals(user.getEmail(), "miemie@baomidou.com");

        mapper.update(
                new User().setEmail("miemie2@baomidou.com"),
                Wrappers.<User>lambdaUpdate()
                        .set(User::getAge, null)
                        .eq(User::getId, 2)
        );
        user = mapper.selectById(2);
        assertEquals(user.getEmail(), "miemie2@baomidou.com");
        assertTrue(user.getAge() == null);
    }

    @Test
    public void testSelect() {
        mapper.insert(
                new User().setId(10086L).setName("中国移动").setEmail("10086@139.com").setAge(3)
        );
        assertEquals(mapper.selectById(10086L).getEmail(), "10086@139.com");
        User user = mapper.selectOne(new QueryWrapper<User>().lambda().eq(User::getId, 10086L));
        assertEquals(user.getName(), "中国移动");
        assertEquals(user.getAge().intValue(), 3);

        mapper.selectList(Wrappers.<User>lambdaQuery().select(User::getId))
                .forEach(x -> {
                    assertTrue(x.getId() != null);
                    assertTrue(x.getEmail() == null);
                    assertEquals("", x.getName(), null);
                    assertEquals("", x.getAge(), null);
                });

        mapper.selectList(new QueryWrapper<User>().select("id", "name"))
                .forEach(x -> {
                    assertTrue(x.getId() != null);
                    assertTrue(x.getName() != null);
                    assertTrue(x.getAge() == null);
                    assertTrue(x.getEmail() == null);
                });
    }
}