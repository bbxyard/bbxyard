package com.imooc.dubbo.m1.server.controller;


import com.imooc.dubbo.m1.api.enums.StatusCode;
import com.imooc.dubbo.m1.api.response.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/base")
public class BaseController {

    // private static final String prefix = "base";

    /**
     * 测试hallo
     */
    @RequestMapping("hallo")
    public BaseResponse hallo(@RequestParam String param) {
        BaseResponse resp = new BaseResponse(StatusCode.Success);
        try {
            resp.setData(param);
        } catch (Exception e) {
            e.printStackTrace();
            resp = new BaseResponse(StatusCode.Fail);
        }
        System.out.println("out: " + resp);
        return resp;
    }

}
