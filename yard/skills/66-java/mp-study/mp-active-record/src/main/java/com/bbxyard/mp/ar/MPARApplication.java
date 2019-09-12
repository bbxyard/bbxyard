package com.bbxyard.mp.ar;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.bbxyard.mp.ar.mapper")
public class MPARApplication {

    public static void main(String[] args) {
        SpringApplication.run(MPARApplication.class, args);
    }

}
