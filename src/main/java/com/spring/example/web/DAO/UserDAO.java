package com.spring.example.web.DAO;

import com.spring.example.web.model.User;

import java.util.List;

public interface UserDAO {

    List<User> getAllUsers();

    void save(User user);

    void delete(Long id);

    void update(User user);

    User findUser(Long id);

}
