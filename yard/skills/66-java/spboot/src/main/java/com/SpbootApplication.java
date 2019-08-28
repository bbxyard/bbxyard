package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;


@SpringBootApplication
// 扫描MyBatis mapper包路径
@MapperScan(basePackages = "com.imooc.mapper")
// 扫描所有需要包, 包含一些自用的工具包 所在路径
@ComponentScan(basePackages = {"com.imooc", "com.bbxyard.spboot", "org.n3r.idworker"})
public class SpbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpbootApplication.class, args);
    }

}
