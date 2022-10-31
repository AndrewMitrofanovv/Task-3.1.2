package com.spring.example.web.service;

import com.spring.example.web.model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    void save(User user);

    void delete(Long id);

    void update(User user);

    User findUser(Long id);
}
