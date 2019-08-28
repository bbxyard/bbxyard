package com.imooc.controller;


import com.bbxyard.spboot.dto.HttpRespMsg;
import com.imooc.pojo.SysUser;
import com.imooc.service.SysUserService;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.util.StringUtils;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("mybatis/sys_user")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private Sid sid;

    @RequestMapping("/create")
    public HttpRespMsg saveUser(String id) throws Exception {
        String userId = StringUtils.isEmptyOrWhitespace(id) ? sid.nextShort() : id;
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

    @RequestMapping("/delete")
    public HttpRespMsg deleteUser(String id) {
        sysUserService.deleteUser(id);
        return HttpRespMsg.Ok("删除成功");
    }

    @RequestMapping("/update")
    public HttpRespMsg updateUser(String id, String u, String nick) {
        SysUser user = new SysUser();
        user.setId(id);
        user.setUsername(u);
        user.setNickname(nick);
        user.setPassword("*********");
        user.setIsDelete(0);
        user.setRegistTime(new Date());

        sysUserService.updateUser(user);
        return HttpRespMsg.Ok("更新成功");
    }

    @RequestMapping("/query_by_id")
    public HttpRespMsg queryUserById(String id) {
        SysUser sysUser = sysUserService.queryUserById(id);
        return HttpRespMsg.Ok(sysUser);
    }

    @RequestMapping("/query_all")
    public HttpRespMsg query_all(Integer p, Integer size) {
        SysUser user = new SysUser();
        List<SysUser> list = null;
        if (p != null) {
            if (size == null) size = 10;
            list = sysUserService.queryUserListPaged(user, p, size);
        } else {
            list = sysUserService.queryUserList(user);
        }
        return HttpRespMsg.Ok(list);
    }
}
