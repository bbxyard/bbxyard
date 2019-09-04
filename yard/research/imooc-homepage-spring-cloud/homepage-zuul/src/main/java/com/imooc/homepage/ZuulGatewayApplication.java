package com.imooc.homepage;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;


/**
 * <h1>网关应用程序</h1>
 * EnableZuulProxy: 标识当前的应用是 Zuul Server
 * SpringCloudApplication: 用于简化配置的组合注解
 * Created by Qinyi.
 */
@EnableZuulProxy
@SpringCloudApplication
public class ZuulGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZuulGatewayApplication.class, args);
    }

}
