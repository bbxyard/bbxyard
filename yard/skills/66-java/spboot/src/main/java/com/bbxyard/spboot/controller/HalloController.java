package com.bbxyard.spboot.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HalloController {

    @RequestMapping("/hallo")
    public Object hallo() {
        return "<h1>Just do it.</h1>";
    }
}
