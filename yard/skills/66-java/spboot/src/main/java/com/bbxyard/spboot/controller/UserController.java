package com.bbxyard.spboot.controller;

import com.bbxyard.spboot.dto.HttpRespMsg;
import com.bbxyard.spboot.entity.User;
import com.bbxyard.spboot.enums.Gender;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/5972")
    public Object getUser() {
        User u = new User("Harry Poter", 19, Gender.MALE);
        HttpRespMsg msg = new HttpRespMsg(u, 200);
        return msg;
    }


    @RequestMapping("/1949")
    public Object getUserCn() {
        User u = new User("PRC", 70, Gender.UNK);
        HttpRespMsg msg = new HttpRespMsg(u, 200);
        return msg;
    }
}
