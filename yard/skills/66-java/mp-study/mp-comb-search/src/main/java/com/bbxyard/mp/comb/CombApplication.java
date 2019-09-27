package com.bbxyard.mp.comb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.bbxyard.mp.comb.mapper")
public class CombApplication {

    public static void main(String[] args) {
        SpringApplication.run(CombApplication.class, args);
    }

}
