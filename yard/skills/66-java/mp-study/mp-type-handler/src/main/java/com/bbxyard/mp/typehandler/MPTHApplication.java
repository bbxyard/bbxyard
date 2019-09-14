package com.bbxyard.mp.typehandler;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan("com.bbxyard.mp.typehandler.mapper")
public class MPTHApplication {

    public static void main(String[] args) {
        SpringApplication.run(MPTHApplication.class, args);
    }

}
