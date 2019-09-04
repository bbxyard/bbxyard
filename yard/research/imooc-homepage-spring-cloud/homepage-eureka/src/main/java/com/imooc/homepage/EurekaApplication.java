package com.imooc.homepage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;


/**
 * 1. 只需要使用 @EnableEurekaServer 注解就可以让应用变为 Eureka Server
 * 2. pom 文件中对应到 spring-cloud-starter-netflix-eureka-server
 * Created by Qinyi.
 */
@EnableEurekaServer
@SpringBootApplication
public class EurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaApplication.class, args);
    }

}
