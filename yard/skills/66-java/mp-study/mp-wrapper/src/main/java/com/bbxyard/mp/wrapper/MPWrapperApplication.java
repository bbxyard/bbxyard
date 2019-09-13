package com.bbxyard.mp.wrapper;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan("com.bbxyard.mp.wrapper.mapper")
public class MPWrapperApplication {

    public static void main(String[] args) {
        SpringApplication.run(MPWrapperApplication.class, args);
    }

}
