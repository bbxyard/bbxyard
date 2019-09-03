package com.bbxyard.mp.xenum;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.bbxyard.mp.xenum.mapper")
public class XEnumApplication {
    public static void main(String[] args) {
        SpringApplication.run(XEnumApplication.class, args);
    }
}
