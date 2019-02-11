package com.example.blog.services;

import com.example.blog.model.Message;
import com.example.blog.util.exception.NotFoundException;

import java.util.List;

public interface MessageService {

    Message create(Message message, int userId);

    void update(Message message, int userId) throws NotFoundException;

    void delete(int id, int userId) throws NotFoundException;

    Message get(int id);

    List<Message> getAll(int userId);

    List<Message> getAll();
}
