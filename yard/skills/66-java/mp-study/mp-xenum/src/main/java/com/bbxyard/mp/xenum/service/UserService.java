package com.bbxyard.mp.xenum.service;

import com.bbxyard.mp.xenum.entity.User;

import java.util.List;

public interface UserService {

    List<User> getAll();

    User getById(Long id);

    void deleteById(Long id);

    void save(User user);
}
