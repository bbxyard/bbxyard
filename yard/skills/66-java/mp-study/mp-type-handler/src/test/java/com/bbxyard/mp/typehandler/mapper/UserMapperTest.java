package com.bbxyard.mp.typehandler.mapper;


import com.bbxyard.mp.typehandler.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserMapperTest {

    @Resource
    private UserMapper mapper;

    @Test
    public void test() {
        User user = mapper.selectById(1L);
        System.err.println(user.getName());

        User user2 = mapper.selectById(1L);
        System.err.println(user2.getName());
    }

}
