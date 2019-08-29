package com.imooc.service;

import com.imooc.pojo.SysUser;

import java.util.List;

/**
 * 系统用户表访问类
 */
public interface SysUserService {

    /**
     * 保存用户
     *
     * @param user
     * @throws Exception
     */
    public void saveUser(SysUser user) throws Exception;

    /**
     * 更新用户
     *
     * @param user
     */
    public void updateUser(SysUser user);

    /**
     * 删除用户
     *
     * @param userId
     */
    public void deleteUser(String userId);

    /**
     * 根据id查询用户
     *
     * @param userId
     * @return
     */
    public SysUser queryUserById(String userId);

    /**
     * 查询用户列表
     *
     * @param user
     * @return
     */
    public List<SysUser> queryUserList(SysUser user);

    /**
     * 分页查询用户列表
     *
     * @param user
     * @param page
     * @param pageSize
     * @return
     */
    public List<SysUser> queryUserListPaged(SysUser user, Integer page, Integer pageSize);

    /**
     * 自定义查询by id
     *
     * @param id
     * @return
     */
    public SysUser queryUserByIdCustom(String id);

    /**
     * 保存用户，基于事务
     *
     * @param user
     */
    public void saveUserTransactional(SysUser user);
}
