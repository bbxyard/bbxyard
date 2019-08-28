package com.imooc.controller;


import com.bbxyard.spboot.dto.HttpRespMsg;
import com.imooc.pojo.SysUser;
import com.imooc.service.SysUserService;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("mybatis")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private Sid sid;

    @RequestMapping("/saveUser")
    public HttpRespMsg saveUser() throws Exception {
        String userId = sid.nextShort();
        SysUser sysUser = new SysUser();
        sysUser.setId(userId);
        sysUser.setNickname("nick" + new Date());
        sysUser.setUsername("user" + new Date());
        sysUser.setPassword("imooc-study");
        sysUser.setIsDelete(0);
        sysUser.setRegistTime(new Date());

        sysUserService.saveUser(sysUser);
        return HttpRespMsg.Ok("保存成功");
    }
}
