package com.imooc.service.impl;

import com.imooc.mapper.SysUserMapper;
import com.imooc.pojo.SysUser;
import com.imooc.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public void saveUser(SysUser user) throws Exception {
        sysUserMapper.insert(user);
    }

    @Override
    public void updateUser(SysUser user) {

    }

    @Override
    public void deleteUser(SysUser user) {

    }

    @Override
    public SysUser queryUserById(String userId) {
        return null;
    }

    @Override
    public List<SysUser> queryUserList(SysUser user) {
        return null;
    }

    @Override
    public List<SysUser> queryUserListPaged(SysUser user, Integer page, Integer pageSize) {
        return null;
    }

    @Override
    public SysUser queryUserByIdCustom(String userId) {
        return null;
    }

    @Override
    public void saveUserTransactional(SysUser user) {

    }
}
