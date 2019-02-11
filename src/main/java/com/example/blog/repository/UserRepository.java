package com.example.blog.repository;

import com.example.blog.model.User;

import java.util.List;

public interface UserRepository {
    User save(User user);

    boolean delete(int id);

    User get(int id);

    User findByLogin(String login);

    List<User> getAll();
}
