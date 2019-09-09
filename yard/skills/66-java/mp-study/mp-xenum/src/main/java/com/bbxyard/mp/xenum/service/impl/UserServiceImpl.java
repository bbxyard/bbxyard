package com.bbxyard.mp.xenum.service.impl;

import com.bbxyard.mp.xenum.entity.User;
import com.bbxyard.mp.xenum.mapper.UserMapper;
import com.bbxyard.mp.xenum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> getAll() {
        return userMapper.selectList(null);
    }

    @Override
    public User getById(Long id) {
        return userMapper.selectById(id);
    }

    @Override
    public void deleteById(Long id) {
        userMapper.deleteById(id);
    }

    @Override
    public void save(User user) {
        if (user.getId() != null) {
            userMapper.updateById(user);
        } else {
            userMapper.insert(user);
        }
    }
}
