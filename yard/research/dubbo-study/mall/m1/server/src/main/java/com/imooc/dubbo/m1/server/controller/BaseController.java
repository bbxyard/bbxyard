package com.imooc.dubbo.m1.server.controller;


import com.imooc.dubbo.m1.api.enums.StatusCode;
import com.imooc.dubbo.m1.api.response.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class BaseController {

    private static final String prefix = "base";

    /**
     * 测试hallo
     */
    public BaseResponse hallo(@RequestParam String param) {
        BaseResponse resp = new BaseResponse(StatusCode.Success);
        try {
            resp.setData(param);
        } catch (Exception e) {
            e.printStackTrace();
            resp = new BaseResponse(StatusCode.Fail);
        }
        return resp;
    }

}
