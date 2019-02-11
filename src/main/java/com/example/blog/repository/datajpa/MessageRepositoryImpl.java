package com.example.blog.repository.datajpa;

import com.example.blog.model.Message;
import com.example.blog.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MessageRepositoryImpl implements MessageRepository {
    private final CrudMessageRepository messageRepo;
    private final CrudUserRepository userRepo;

    @Autowired
    public MessageRepositoryImpl(CrudMessageRepository messageRepo, CrudUserRepository userRepo) {
        this.messageRepo = messageRepo;
        this.userRepo = userRepo;
    }

    @Override
    public Message save(Message message, int userId) {
        if (!message.isNew() && get(message.getId()) == null) {
            return null;
        }
        message.setUser(userRepo.getOne(userId));
        return messageRepo.save(message);
    }

    @Override
    public boolean delete(int id, int userId) {
        return messageRepo.delete(id, userId) != 0;
    }

    @Override
    public Message get(int id) {
        return messageRepo.findById(id).orElse(null);
    }

    @Override
    public List<Message> getAll(int userId) {
        return messageRepo.findAll(userId);
    }

    @Override
    public List<Message> getAll() {
        return messageRepo.findAll();
    }
}
