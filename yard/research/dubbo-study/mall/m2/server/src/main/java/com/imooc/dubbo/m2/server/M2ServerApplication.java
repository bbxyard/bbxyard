package com.imooc.dubbo.m2.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class M2ServerApplication {

    public static void main(String[] args) {
        System.out.println(M2ServerApplication.class);
        SpringApplication.run(M2ServerApplication.class, args);
    }

}
