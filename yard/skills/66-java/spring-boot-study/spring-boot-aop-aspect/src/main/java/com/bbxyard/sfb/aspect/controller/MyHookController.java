package com.bbxyard.sfb.aspect.controller;


import com.bbxyard.sfb.aspect.anno.MyHook;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class MyHookController {

    @MyHook("测试一下我们的钩子")
    @RequestMapping("/hallo")
    public String hallo(@RequestParam("name") String name) {
        String out = String.format("<h1>hallo %s</h1> ", name);
        return out;
    }

}
