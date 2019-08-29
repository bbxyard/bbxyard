package com.imooc.controller.interceptor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("hook/one")
public class OneController {

    @RequestMapping("/index")
    public String index(ModelMap map) {
        map.addAttribute("name", "hook/one/index");
        return "thymeleaf/index";
    }

    @RequestMapping("/center")
    public String center() {
        return "thymeleaf/center/center";
    }
}
