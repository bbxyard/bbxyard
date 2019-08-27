package com.bbxyard.spboot.controller;

import com.bbxyard.spboot.dto.HttpRespMsg;
import com.bbxyard.spboot.entity.WebUser;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/user")
public class WebUserController {

    @RequestMapping("/80")
    public Object getUser() {
        WebUser u = new WebUser();
        u.setName("admin");
        u.setPassword("**secret**");
        u.setLastLogin(new Date());
        u.setAge(24);
        HttpRespMsg msg = new HttpRespMsg(u, 200);
        return msg;
    }

}
