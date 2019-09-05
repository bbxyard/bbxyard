package com.imooc.homepage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * <h1>测试用例启动入口</h1>
 * Created by Qinyi.
 */
@EnableFeignClients(basePackages = {"com.imooc.homepage"})
@SpringBootApplication
public class UserApplicationTest {

    public static void main(String[] args) {
        SpringApplication.run(UserApplicationTest.class, args);
    }
}
