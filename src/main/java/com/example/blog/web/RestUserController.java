package com.example.blog.web;

import com.example.blog.model.User;
import com.example.blog.services.UserService;
import com.example.blog.to.UserTo;
import com.example.blog.util.UserUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

import static com.example.blog.util.ValidationUtil.assureIdConsistent;
import static com.example.blog.util.ValidationUtil.checkNew;
import static com.example.blog.web.SecurityUtil.authUserId;

@RestController
@RequestMapping(value = RestUserController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestUserController {
    static final String REST_URL = "/rest/profile";
    private final Logger log;
    private final UserService service;

    @Autowired
    public RestUserController(UserService service) {
        this.service = service;
        this.log = LoggerFactory.getLogger(getClass());
    }

    @GetMapping
    public User get() {
        int id = authUserId();
        log.info("get {}", id);
        return service.get(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Integer id) {
//        int id = authUserId();
        log.info("delete user {}", id);
        service.delete(id);
    }

    @DeleteMapping
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete() {
        System.out.println("delete");
        int id = authUserId();
        log.info("delete user {}", id);
        service.delete(id);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@RequestBody User user, BindingResult result) {
        result.getAllErrors().forEach(System.out::println);

        int id = authUserId();
        assureIdConsistent(user, user.getId());
        log.info("update {} for user {}", user, id);
        service.update(user);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<User> register(@Valid @RequestBody UserTo userTo) {
        log.info("create {}", userTo);
        checkNew(userTo);
        User created = service.create(UserUtil.createNewFromTo(userTo));
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}
