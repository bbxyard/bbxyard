package com.bbxyard.mp.zz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bbxyard.mp.zz.entity.User;
import com.bbxyard.mp.zz.mapper.UserMapper;
import com.bbxyard.mp.zz.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
