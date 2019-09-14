package com.bbxyard.mp.tenant;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan("com.bbxyard.mp.tenant.mapper")
public class MPTenantApplication {

    public static void main(String[] args) {
        SpringApplication.run(MPTenantApplication.class, args);
    }

}
