package com.bbxyard.mp.metainfo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.bbxyard.mp.metainfo.mapper")
public class MPAutoFillMetaInfoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MPAutoFillMetaInfoApplication.class, args);
    }

}
