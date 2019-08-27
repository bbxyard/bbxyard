package com.bbxyard.spboot.controller;

import com.bbxyard.spboot.entity.User;
import com.bbxyard.spboot.enums.Gender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class OldUserController {

    @ResponseBody
    @RequestMapping("/3389")
    public User getUser() {
        User u = new User("RDP", 3389, Gender.FEMALE);
        return u;
    }

}
