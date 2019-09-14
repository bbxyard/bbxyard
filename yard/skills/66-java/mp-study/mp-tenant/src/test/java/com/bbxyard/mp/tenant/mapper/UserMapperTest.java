package com.bbxyard.mp.tenant.mapper;

import com.bbxyard.mp.tenant.entity.User;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserMapperTest {

    @Resource
    private UserMapper mapper;


    @Test
    public void aInsert() {
        User user = new User();
        user.setName("自动添加租客ID");
        // user.setTenantId(1L); 自动添加
        Assert.assertTrue(mapper.insert(user) > 0);
        user = mapper.selectById(user.getId());
        Assert.assertTrue(1L == user.getTenantId());
    }

    @Test
    public void bDelete() {
        Assert.assertTrue(mapper.deleteById(3L) > 0);
        Assert.assertTrue(mapper.deleteById(4L) > 0);
    }

    @Test
    public void cUpdate() {
        assertTrue(mapper.updateById(new User().setId(1L).setName("mp")) > 0);
    }

    @Test
    public void dSelect() {
        List<User> users = mapper.selectList(null);
        users.forEach(System.out::println);
        users.forEach(u -> assertTrue(u.getTenantId() == 1L));
    }

    /**
     * 自定义SQL：默认也会增加多租户条件
     * 参考打印的SQL
     */
    @Test
    public void manualSqlTenantFilterTest() {
        System.out.println(mapper.myCount());
    }

    @Test
    public void testTenantFilter() {
        mapper.getAddrAndUser().forEach(System.out::println);
    }
}