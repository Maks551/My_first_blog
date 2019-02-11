package com.example.blog.services;

import com.example.blog.AuthorizedUser;
import com.example.blog.model.User;
import com.example.blog.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

import static com.example.blog.util.UserUtil.prepareToSave;
import static com.example.blog.util.ValidationUtil.checkNotFound;
import static com.example.blog.util.ValidationUtil.checkNotFoundWithId;

@Service("userService")
public class UserServiceImpl implements UserService, UserDetailsService {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User get(int id) {
        return checkNotFoundWithId(repository.get(id), id);
    }

    @Override
    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id), id);
    }

    @Override
    public void update(User user) {
        Assert.notNull(user, "user must not be null");
        checkNotFoundWithId(repository.save(prepareToSave(user, passwordEncoder)), user.getId());

    }

    @Override
    public User create(User user) {
        Assert.notNull(user, "user must not be null");
        return repository.save(prepareToSave(user, passwordEncoder));
    }

    @Override
    public List<User> getAll() {
        return repository.getAll();
    }

    @Override
    public User findByLogin(String login) {
        Assert.notNull(login, "username must not be null");
        return checkNotFound(repository.findByLogin(login), "login=" + login);
    }

    @Override
    public void autologin(String username, String password) {
        AuthorizedUser authorizedUser = loadUserByUsername(username);
        Authentication authentication = new UsernamePasswordAuthenticationToken(authorizedUser, password, authorizedUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        log.debug(String.format("Auto login %s successfully!", username));
    }

    @Override
    public AuthorizedUser loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByLogin(username.trim().toLowerCase());
        if (user == null) {
            throw new UsernameNotFoundException("User " + username + " is not found");
        }
        return new AuthorizedUser(user);
    }
}
