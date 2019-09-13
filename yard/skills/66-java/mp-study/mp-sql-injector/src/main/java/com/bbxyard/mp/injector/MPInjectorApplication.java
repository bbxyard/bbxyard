package com.bbxyard.mp.injector;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
// @MapperScan("com.bbxyard.mp.injector.mapper")
public class MPInjectorApplication {

    public static void main(String[] args) {
        SpringApplication.run(MPInjectorApplication.class, args);
    }

}
