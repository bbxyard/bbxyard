package com.bbxyard.spboot.controller;

import com.imooc.pojo.SysUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/th")
public class ThymeleafController {

    @RequestMapping("/index")
    public String index(ModelMap map) {
        map.addAttribute("name", "thymeleaf-imooc");
        return "thymeleaf/index";
    }

    @RequestMapping("/center")
    public String center() {
        return "thymeleaf/center/center";
    }

    @RequestMapping("/test")
    public String test(ModelMap map) {
        SysUser sysUser = new SysUser();
        sysUser.setNickname("Thymeleaf");
        sysUser.setAge(124);
        sysUser.setPassword("ABCD1234");
        sysUser.setLastLoginTime(new Date());
        sysUser.setAddress("<font color='green'><b>北京市 海淀区 五道口</b></font>");
        map.addAttribute("user", sysUser);

        List<SysUser> userList = new ArrayList<>();
        userList.add(sysUser);
        for (int i = 0; i < 5; ++i) {
            SysUser user = new SysUser();
            user.setNickname("Freemarker-" + i);
            user.setAge(16 + i);
            user.setPassword("ABCD1234");
            user.setLastLoginTime(new Date());
            user.setAddress("北京市 海淀区 五道口");
            userList.add(user);
        }
        map.addAttribute("userList", userList);

        return "thymeleaf/test";
    }

    @RequestMapping("postform")
    public String postform(SysUser user) {
        System.out.println("姓名: " + user.getNickname());
        System.out.println("年龄: " + user.getAge());
        return "redirect:/th/test";
    }
}

