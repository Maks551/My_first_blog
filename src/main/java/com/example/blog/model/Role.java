package com.example.blog.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    PUBLISHER,
    USER,
    ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
