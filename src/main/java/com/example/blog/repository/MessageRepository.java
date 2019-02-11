package com.example.blog.repository;

import com.example.blog.model.Message;

import java.util.List;

public interface MessageRepository {
    Message save(Message message, int userId);

    boolean delete(int id, int userId);

    Message get(int id);

    List<Message> getAll(int userId);

    List<Message> getAll();
}
