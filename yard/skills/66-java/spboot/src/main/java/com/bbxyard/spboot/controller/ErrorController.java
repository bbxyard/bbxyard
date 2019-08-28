package com.bbxyard.spboot.controller;

import com.bbxyard.spboot.dto.HttpRespMsg;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/err")
public class ErrorController {

    @RequestMapping("/error")
    public String error() {
        int a = 1 / 0;
        System.out.println("以下代码，不会可见");
        return "将会被 [" + "/error" + "] 处理";
    }

    @RequestMapping("/ajaxerror")
    public String ajaxerror() {
        return "thymeleaf/ajaxerror";
    }


    @RequestMapping("/getAjaxerror")
    @ResponseBody
    public HttpRespMsg getAjaxerror() {
        int a = 1 / 0;
        String s = "Ajax错误处理ed";
        return HttpRespMsg.Ok(s);
    }
}
