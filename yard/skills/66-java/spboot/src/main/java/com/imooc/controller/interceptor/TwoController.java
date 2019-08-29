package com.imooc.controller.interceptor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("hook/two")
public class TwoController {

    @RequestMapping("/index")
    public String getIndex(ModelMap map) {
        map.addAttribute("name", "hook/two/index");
        return "thymeleaf/index";
    }

    @RequestMapping("/center")
    public String getCenter() {
        return "thymeleaf/center/center";
    }

}
