package com.bbxyard.mp.demo.controller;

import com.bbxyard.mp.demo.dto.HttpRespMsg;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @RequestMapping("list")
    public Object listAll() {
        return HttpRespMsg.Ok("Just do it.");
    }

}
