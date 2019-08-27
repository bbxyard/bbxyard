package com.bbxyard.spboot.controller;

import com.bbxyard.spboot.dto.HttpRespMsg;
import com.bbxyard.spboot.pojo.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/imooc")
public class SrcCodeController {

    @Autowired
    private Resource resource;

    @RequestMapping("/code")
    public Object getResource() {
        Resource r = new Resource();
        BeanUtils.copyProperties(resource, r);
        return HttpRespMsg.Ok(r);
    }
}
