package com.bbxyard.mp.zz.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.additional.query.impl.LambdaQueryChainWrapper;
import com.bbxyard.mp.zz.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

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

    @Test
    public void testPagination() {
        IPage<User> pager = new Page<>(2, 2, true);
        LambdaQueryWrapper<User> wrapper = Wrappers.<User>lambdaQuery();
        // LambdaQueryChainWrapper<User> wrapper = userService.lambdaQuery();
        wrapper.ge(User::getAge, 10).orderByDesc(User::getAge);

        // general type - User
        IPage<User> res = userService.page(pager, wrapper);
        System.out.println(String.format("summar: total %d/%d/%d", res.getTotal(), res.getPages(), res.getCurrent()));
        displayUsers(res.getRecords());

        // general type - Object Map
        IPage<Map<String, Object>> mapRes = userService.pageMaps(pager, wrapper);
        System.out.println(String.format("summar: total %d/%d/%d", mapRes.getTotal(), mapRes.getPages(), mapRes.getCurrent()));
        this.displayRecords(mapRes.getRecords());
    }

    private void displayUsers(List<User> users) {
        System.out.println("Users: ");
        for (User user : users) {
            System.out.println(" ==> " + user);
        }
    }

    private void displayRecords(List<Map<String, Object>> rows) {
        System.out.println("Rows: ");
        for (Map<String, Object> row : rows) {
            System.out.println(" --> " + row);
        }
    }
}
