package com.imooc.dubbo.m1.server.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Hallo {

    private Logger log = LoggerFactory.getLogger(Hallo.class);

    @RequestMapping("/")
    public Object hallo() {
        log.info("Just do it. hahaha");
        return "Just do it";
    }

}
