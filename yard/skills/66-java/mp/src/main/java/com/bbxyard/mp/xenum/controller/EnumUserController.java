package com.bbxyard.mp.xenum.controller;

import com.bbxyard.mp.xenum.entity.User;
import com.bbxyard.mp.xenum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/enum")
public class EnumUserController {

    @Autowired
    private UserService userService;

    @RequestMapping("list")
    public Object listAll() {
        List<User> list = userService.getAll();
        return list;
    }

    @RequestMapping("get")
    public Object get(Integer id) {
        User user = userService.getById(id);
        return user;
    }
}
