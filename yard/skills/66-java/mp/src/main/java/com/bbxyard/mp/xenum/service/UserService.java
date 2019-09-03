package com.bbxyard.mp.xenum.service;

import com.bbxyard.mp.xenum.entity.User;
import com.bbxyard.mp.xenum.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public List<User> getAll() {
        return userMapper.selectList(null);
    }

    public User getById(Integer id) {
        return userMapper.selectById(id);
    }

    public void deleteById(Integer id) {
        userMapper.deleteById(id);
    }

    public void save(User user) {
        if (user.getId() != null) {
            userMapper.updateById(user);
        } else {
            userMapper.insert(user);
        }
    }
}

