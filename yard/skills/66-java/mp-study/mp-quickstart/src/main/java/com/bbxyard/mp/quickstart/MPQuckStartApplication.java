package com.bbxyard.mp.quickstart;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.bbxyard.mp.quickstart.mapper")
public class MPQuckStartApplication {

    public static void main(String[] args) {
        SpringApplication.run(MPQuckStartApplication.class, args);
    }

}
