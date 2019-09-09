package com.bbxyard.mp.xenum.controller;

import com.bbxyard.mp.xenum.entity.User;
import com.bbxyard.mp.xenum.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("xenum")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("list")
    public Object getAll() {
        List<User> list = userService.getAll();
        log.info("getAll: ", list);
        return list;
    }

}
