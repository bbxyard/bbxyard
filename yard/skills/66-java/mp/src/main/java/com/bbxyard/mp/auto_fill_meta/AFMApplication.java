package com.bbxyard.mp.auto_fill_meta;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.bbxyard.mp.auto_fill_meta.mapper")
public class AFMApplication {

    public static void main(String[] args) {
        SpringApplication.run(AFMApplication.class, args);
    }

}

