package com.bbxyard.mp.opt_lock;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.bbxyard.mp.opt_lock.mapper")
public class OptLockApplication {

    public static void main(String[] args) {
        SpringApplication.run(OptLockApplication.class, args);
    }

}
