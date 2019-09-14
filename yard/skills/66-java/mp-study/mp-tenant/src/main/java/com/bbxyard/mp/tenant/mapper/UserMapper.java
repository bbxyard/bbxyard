package com.bbxyard.mp.tenant.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bbxyard.mp.tenant.entity.User;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {
    /**
     * 自定义SQL：默认也会增加多租户条件
     * 参考打印的SQL
     * @return
     */
    Integer myCount();

    List<User> getUserAndAddr();

    List<User> getAddrAndUser();
}
