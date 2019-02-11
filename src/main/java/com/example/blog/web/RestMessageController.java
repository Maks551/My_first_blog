package com.example.blog.web;

import com.example.blog.model.Message;
import com.example.blog.services.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static com.example.blog.util.ValidationUtil.assureIdConsistent;
import static com.example.blog.util.ValidationUtil.checkNew;
import static com.example.blog.web.SecurityUtil.authUserId;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = RestMessageController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestMessageController {
    static final String REST_URL = "/rest/profile/message";
    private final Logger log;
    private final MessageService service;

    public RestMessageController(MessageService service) {
        this.service = service;
        this.log = LoggerFactory.getLogger(getClass());
    }

    @GetMapping("/{id}")
    public Message get(@PathVariable("id") int id) {
        int userId = authUserId();
        log.info("get message entry {} for user {}", id, userId);
        return service.get(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id) {
        int userId = authUserId();
        log.info("delete message entry {} for user {}", id, userId);
        service.delete(id, userId);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@RequestBody Message message) {
        int userId = authUserId();
        assureIdConsistent(message, message.getId());
        log.info("update {} for user {}", message, userId);
        service.update(message, userId);
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Message> createWithLocation(@RequestBody Message message) {
        int userId = authUserId();
        log.info("create {} for user {}", message, userId);
        checkNew(message);
        Message created = service.create(message, userId);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @GetMapping("/all")
    public List<Message> getAll() {
        int userId = authUserId();
        log.info("get all messages for user {}", userId);
        return service.getAll();
    }

    @GetMapping("/all-by-user")
    public List<Message> getAllByUser() {
        int userId = authUserId();
        log.info("get all messages by user {}", userId);
        return service.getAll(userId);
    }
}
