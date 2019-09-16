package com.bbxyard.mq.kafka;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan("com.bbxyard.mq.kafka.mapper")
public class MQKHApplication {

    public static void main(String[] args) {
        SpringApplication.run(MQKHApplication.class, args);
    }

}
