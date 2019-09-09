package com.bbxyard.mp.annotation;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.bbxyard.mp.annotation.mapper")
public class MPAnnotationApplication {

    public static void main(String[] args) {
        SpringApplication.run(MPAnnotationApplication.class, args);
    }

}
