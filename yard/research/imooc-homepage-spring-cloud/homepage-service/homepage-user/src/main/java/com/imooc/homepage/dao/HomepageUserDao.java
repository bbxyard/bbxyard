package com.imooc.homepage.dao;

import com.imooc.homepage.entity.HomepageUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HomepageUserDao extends JpaRepository<HomepageUser, Long> {

    /**
     * <h2>通过用户名寻找数据记录</h2>
     * */
    HomepageUser findByUsername(String username);

}
