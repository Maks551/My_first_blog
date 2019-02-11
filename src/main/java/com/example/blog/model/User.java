package com.example.blog.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jdk.nashorn.internal.runtime.regexp.RegExp;
import org.hibernate.annotations.BatchSize;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = "login", name = "users_unique_login_idx")})
public class User extends AbstractBaseEntity implements Serializable {

    @NotBlank
    @Size(min = 3, max = 100)
    @Column(name = "login", unique = true)
    private String login;

    @NotBlank
    @Size(min = 5, max = 100)
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "password")
    private String password;

    @NotNull
//    @Pattern(regexp = "^(" + getPassword() + ")$")
//    @Pattern(regexp = password)
    @Column(name = "first_name", columnDefinition="varchar(100) default ''")
    private String firstName;

    @NotNull
    @Column(name = "last_name", columnDefinition="varchar(100) default ''")
    private String lastName;

    @Column(name = "enabled", columnDefinition = "bit default 1")
    private boolean enabled = true;

    @Column(name = "registered", columnDefinition = "timestamp default now()")
    @NotNull
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date registered = new Date();

    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    @ElementCollection(fetch = FetchType.EAGER)
    @BatchSize(size = 200)
    private Set<Role> roles;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    @OrderBy("dateTime DESC")
    @JsonIgnore
    private List<Message> messages;

    public User() {
    }

    public User(Integer id, String login, String password, String firstName, String lastName, Role role, Role... roles) {
        this(id, login, password, firstName, lastName, true, new Date(), EnumSet.of(role, roles));
    }

    public User(Integer id, String login, String password, String firstName, String lastName, boolean enabled, Date registered, Collection<Role> roles) {
        super(id);
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.enabled = enabled;
        this.registered = registered;
        setRoles(roles);
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Date getRegistered() {
        return registered;
    }

    public void setRegistered(Date registered) {
        this.registered = registered;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    private void setRoles(Collection<Role> roles) {
        this.roles = CollectionUtils.isEmpty(roles) ? Collections.emptySet() : EnumSet.copyOf(roles);
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login=" + login +
                ", firstName=" + firstName +
                ", lastName=" + lastName +
                ", enabled=" + enabled +
                ", registered=" + registered +
                ", roles=" + roles +
                '}';
    }
}
