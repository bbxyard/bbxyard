package com.bbxyard.mp.optlocker;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.bbxyard.mp.optlocker.mapper")
public class MPOptLockerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MPOptLockerApplication.class, args);
    }

}
