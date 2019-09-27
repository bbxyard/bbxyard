package com.bbxyard.mp.autogen.controller;


import com.bbxyard.mp.autogen.entity.AfmUser;
import com.bbxyard.mp.autogen.service.AfmUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author bbxyard
 * @since 2019-09-27
 */
@RestController
@RequestMapping("/afmUser")
public class AfmUserController {

    @Autowired
    private AfmUserService userService;

    @RequestMapping({"/", "index"})
    public Object listAll() {
        List<AfmUser> users = userService.list();
        return users;
    }


}

