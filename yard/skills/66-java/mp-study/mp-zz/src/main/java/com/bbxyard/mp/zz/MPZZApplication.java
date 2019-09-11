package com.bbxyard.mp.zz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.bbxyard.mp.zz.mapper")
public class MPZZApplication {

    public static void main(String[] args) {
        SpringApplication.run(MPZZApplication.class, args);
    }

}
