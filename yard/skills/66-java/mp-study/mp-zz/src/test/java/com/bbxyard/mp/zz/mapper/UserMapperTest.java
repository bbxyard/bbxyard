package com.bbxyard.mp.zz.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bbxyard.mp.zz.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.List;
import java.util.Map;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserMapperTest {

    @Resource
    private UserMapper userMapper;

    @Test
    public void listAllByCustom() {

        LambdaQueryWrapper<User> lambdaQueryWrapper = Wrappers.<User>lambdaQuery();
        lambdaQueryWrapper.likeLeft(User::getName, "子").or().isNotNull(User::getEmail);
        List<User> users = userMapper.listAllByCustom(lambdaQueryWrapper);
        for (User user : users) {
            System.out.println(user);
        }

    }

    @Test
    public void listAllByCustomFromXml() {
        LambdaQueryWrapper<User> lambdaQueryWrapper = Wrappers.<User>lambdaQuery();
        lambdaQueryWrapper.likeLeft(User::getName, "子");
        List<User> users = userMapper.listAllByCustomFromXml(lambdaQueryWrapper);
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testPagination() {
        IPage<User> page = new Page<>(2, 2, true);
        LambdaQueryWrapper<User> lambdaQueryWrapper = Wrappers.<User>lambdaQuery();
        lambdaQueryWrapper.gt(User::getAge, 10);

        // 泛型-1 User
        IPage<User> res = userMapper.selectPage(page, lambdaQueryWrapper);
        System.out.println(String.format("summar: total %d/%d/%d", res.getTotal(), res.getPages(), res.getCurrent()));
        this.displayUsers(res.getRecords());

        // 泛型-2 对象map
        IPage<Map<String, Object>> mapRes = userMapper.selectMapsPage(page, lambdaQueryWrapper);
        System.out.println(String.format("summar: total %d/%d/%d", mapRes.getTotal(), mapRes.getPages(), mapRes.getCurrent()));
        this.displayRecords(mapRes.getRecords());
    }

    @Test
    public void testCustomQueryPagination() {
        Page<User> pager = new Page<>(2, 3);
        LambdaQueryWrapper<User> wrapper = Wrappers.<User>lambdaQuery();
        wrapper.gt(User::getId, 1).orderByDesc(User::getId);
        // IPage<User> res = userMapper.selectPage(pager, wrapper);
        // TODO 自定义查询分页报错了，暂时跳过 @ 2019.09.12
        IPage<User> res = userMapper.listPageByCustom(pager, wrapper);
        System.out.println(String.format("summar: total %d/%d/%d", res.getTotal(), res.getPages(), res.getCurrent()));
        this.displayUsers(res.getRecords());
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
