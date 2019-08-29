package com.imooc.controller.interceptor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("hook/two-one")
public class TwoOneController {

    @RequestMapping("/index")
    public String index(ModelMap map) {
        map.addAttribute("name", "hook/two-one/index");
        return "thymeleaf/index";
    }

}
