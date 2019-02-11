package com.example.blog.util;

import com.example.blog.model.Role;
import com.example.blog.model.User;
import com.example.blog.to.UserTo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;

public final class UserUtil {

    public static UserTo asTo(User user) {
        return new UserTo(user.getId(), user.getLogin(), user.getPassword(), /*user.getFirstName(), user.getLastName(), */user.getPassword());
    }

    public static User updateFromTo(User user, UserTo userTo) {
        user.setLogin(userTo.getUsername());
        user.setPassword(userTo.getPassword());
        return user;
    }

    public static User createNewFromTo(UserTo newUser) {
        return new User(null, newUser.getUsername(), newUser.getPassword(), "", "", Role.USER);
    }

    public static User prepareToSave(User user, PasswordEncoder passwordEncoder) {
        String password = user.getPassword();
        user.setPassword(StringUtils.isEmpty(password) ? password : passwordEncoder.encode(password));
        user.setLogin(user.getLogin().trim().toLowerCase());
        return user;
    }
}
