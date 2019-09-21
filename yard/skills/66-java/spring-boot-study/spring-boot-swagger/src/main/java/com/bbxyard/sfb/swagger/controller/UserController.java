package com.bbxyard.sfb.swagger.controller;


import com.bbxyard.sfb.swagger.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Api("用户信息管理")
@RestController
@RequestMapping("/user")
public class UserController {

    private final static List<User> userList = new ArrayList<>();

    {
        userList.add(new User(141592L, "admin", 6, "pai-6@math.com"));
        userList.add(new User(65358L, "part2", 5, "pai-p2@math.com"));
        userList.add(new User(979323846284L, "part3", 12, "3rd@math.com"));
    }

    @ApiOperation("获取列表")
    @GetMapping("list")
    public List getUserList() {
        return userList;
    }

    @ApiOperation("新增用户")
    @PostMapping("add")
    public boolean addUser(User user) {
        return userList.add(user);
    }

    @ApiOperation("更新用户")
    @ApiImplicitParam(name = "user", value = "单个用户信息", dataType = "User")
    @PutMapping("update")
    public boolean update(User user) {
        return userList.remove(user) && userList.add(user);
    }

    @ApiOperation("批量删除")
    @ApiImplicitParam(name = "users", value = "N个用户信息", dataType = "List<User>")
    @DeleteMapping("delete")
    public boolean delete(@RequestBody List<User> users) {
        return userList.removeAll(users);
    }
}
