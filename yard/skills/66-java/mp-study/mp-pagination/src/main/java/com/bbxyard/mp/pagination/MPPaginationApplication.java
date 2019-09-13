package com.bbxyard.mp.pagination;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.bbxyard.mp.pagination.mapper")
public class MPPaginationApplication {

    public static void main(String[] args) {
        SpringApplication.run(MPPaginationApplication.class, args);
    }

}
