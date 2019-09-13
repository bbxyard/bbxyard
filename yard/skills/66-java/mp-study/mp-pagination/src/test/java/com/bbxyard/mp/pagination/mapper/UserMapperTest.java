package com.bbxyard.mp.pagination.mapper;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bbxyard.mp.pagination.entity.User;
import com.bbxyard.mp.pagination.model.MyPage;
import com.bbxyard.mp.pagination.model.ParamSome;
import com.bbxyard.mp.pagination.model.UserChildren;
import org.apache.ibatis.session.RowBounds;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;


@SpringBootTest
@RunWith(SpringRunner.class)
public class UserMapperTest {

    @Resource
    private UserMapper mapper;

    @Test
    public void lambdaPagination() {
        Page<User> page = new Page<>(1, 3);
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.lambda().ge(User::getAge, 1).orderByAsc(User::getAge);

        IPage<User> result = mapper.selectPage(page, wrapper);
        System.out.println(result.getTotal());
        assertTrue(result.getTotal() > 3);
        assertEquals(3, result.getRecords().size());
    }

    @Test
    public void test1() {
        System.out.println("--- baseMapper 自带分页 [BEGIN] ---");
        Page<User> page = new Page<>(1, 5);
        IPage<User> userIPage = mapper.selectPage(page, new QueryWrapper<User>()
                .eq("age", 20).eq("name", "Jack"));
        // assertTrue();
        System.out.println("总条数 ==> " + userIPage.getTotal());
        System.out.println("当前页数 ==> " + userIPage.getCurrent());
        System.out.println("当前每页显示数 ==> " + userIPage.getSize());
        print(userIPage.getRecords());
        System.out.println("--- baseMapper 自带分页 [END] ---");

        System.out.println("Json 正反序列化 [BEGIN]");
        String json = JSON.toJSONString(page);
        Page<User> page1 = JSON.parseObject(json, Page.class);
        print(page1.getRecords());
        System.out.println("Json 正反序列化 [END]");

        System.out.println("--- 自定义XML分页 [BEGIN] ---");
        MyPage<User> myPage = new MyPage<User>(1, 5).setSelectInt(20).setSelectStr("Jack");
        ParamSome paramSome = new ParamSome(20, "Jack");
        MyPage<User> userMyPage = mapper.mySelectPage(myPage, paramSome);
        System.out.println("总条数 ==> " + userMyPage.getTotal());
        System.out.println("当前页数 ==> " + userMyPage.getCurrent());
        System.out.println("当前每页显示数 ==> " + userMyPage.getSize());
        print(userMyPage.getRecords());
        System.out.println("--- 自定义XML分页 [END] ---");
    }

    @Test
    public void test2() {
        /* 下面的 left join 不会对 count 进行优化,因为 where 条件里有 join 的表的条件 */
        MyPage<UserChildren> myPage = new MyPage<>(1, 5);
        myPage.setSelectInt(18).setSelectStr("Jack");
        MyPage<UserChildren> userChildrenMyPage = mapper.userChildrenPage(myPage);
        List<UserChildren> records = userChildrenMyPage.getRecords();
        records.forEach(System.out::println);

        /* 下面的 left join 会对 count 进行优化,因为 where 条件里没有 join 的表的条件 */
        myPage = new MyPage<UserChildren>(1, 5).setSelectInt(18);
        userChildrenMyPage = mapper.userChildrenPage(myPage);
        records = userChildrenMyPage.getRecords();
        records.forEach(System.out::println);
    }

    @Test
    public void testMyPageMap() {
        MyPage<User> myPage = new MyPage<User>(1, 5).setSelectInt(20).setSelectStr("Jack");
        Map map = new HashMap(1);
        map.put("name", "%a");
        mapper.mySelectPageMap(myPage, map);
        myPage.getRecords().forEach(System.out::println);
    }

    private <T> void print(List<T> list) {
        if (!CollectionUtils.isEmpty(list)) {
            list.forEach(System.out::println);
        }
    }

    @Test
    public void testMap() {
        Map map = new HashMap(1);
        map.put("name", "%a");
        mapper.mySelectMap(map).forEach(System.out::println);
    }

    @Test
    public void myPage() {
        MyPage<User> pager = new MyPage<>(1, 5);
        pager.setName("a");
        mapper.myPageSelect(pager).forEach(System.out::println);
    }

    @Test
    public void iPageTest() {
        IPage<User> pager = new Page<User>(1, 5) {
            private String name = "%";

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        };
        List<User> users = mapper.iPageSelect(pager);
        System.out.println("users.size=" + users.size());
        System.out.println("page.total=" + pager.getTotal());
    }

    @Test
    public void rowBoundsTest() {
        RowBounds rowBounds = new RowBounds(0, 5);
        Map map = new HashMap(1);
        map.put("name", "%");
        List<User> users = mapper.rowBoundList(rowBounds, map);
        System.out.println("users.size=" + users.size());
    }
}