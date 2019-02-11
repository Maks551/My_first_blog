package com.example.blog.services;

import com.example.blog.model.Message;
import com.example.blog.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

import static com.example.blog.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MessageServiceImpl implements MessageService {
    private final MessageRepository repository;

    @Autowired
    public MessageServiceImpl(MessageRepository repository) {
        this.repository = repository;
    }

    @Override
    public Message create(Message message, int userId) {
        Assert.notNull(message, "Message must not be null");
        return repository.save(message, userId);
    }

    @Override
    public void update(Message message, int userId) {
        checkNotFoundWithId(repository.save(message, userId), message.getId());
    }

    @Override
    public void delete(int id, int userId) {
        checkNotFoundWithId(repository.delete(id, userId), id);
    }

    @Override
    public Message get(int id) {
        return checkNotFoundWithId(repository.get(id), id);
    }

    @Override
    public List<Message> getAll(int userId) {
        return repository.getAll(userId);
    }

    @Override
    public List<Message> getAll() {
        return repository.getAll();
    }
}
