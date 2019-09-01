package com.bbxyard.mp.quickstart;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.bbxyard.mp.quickstart.mapper")
public class QSApplication {

    public static void main(String[] args) {
        SpringApplication.run(QSApplication.class, args);
    }

}
