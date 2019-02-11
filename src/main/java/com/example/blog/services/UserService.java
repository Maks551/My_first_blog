package com.example.blog.services;

import com.example.blog.model.User;
import com.example.blog.util.exception.NotFoundException;

import java.util.List;

public interface UserService {

    User get(int id) throws NotFoundException;

    User findByLogin(String login);

    void delete(int id) throws NotFoundException;

    void update(User user) throws NotFoundException;

    User create(User user);

    List<User> getAll();

    void autologin(String username, String password);
}
