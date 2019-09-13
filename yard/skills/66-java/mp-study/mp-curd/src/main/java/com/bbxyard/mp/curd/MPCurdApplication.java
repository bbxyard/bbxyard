package com.bbxyard.mp.curd;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.bbxyard.mp.curd.mapper")
public class MPCurdApplication {

    public static void main(String[] args) {
        SpringApplication.run(MPCurdApplication.class, args);
    }

}
