package com.bbxyard.mp.performance;


import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.bbxyard.mp.performance.mapper")
public class MPPAAplication {

    public static void main(String[] args) {
        SpringApplication.run(MPPAAplication.class, args);
    }

}
