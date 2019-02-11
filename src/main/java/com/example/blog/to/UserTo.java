package com.example.blog.to;

import com.example.blog.HasId;

import javax.validation.ValidationException;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class UserTo implements Serializable, HasId {

    private Integer id;

    @NotBlank
    @Size(min = 3, max = 100)
    private String username;

    @NotBlank
    @Size(min = 5, max = 100)
    private String password;

//    @NotNull
//    @Size(max = 100)
//    private String nickname;

//    @NotNull
//    @Size(max = 100)
//    private String firstName;

//    @NotNull
//    @Size(max = 100)
//    private String lastName;

    @NotBlank
    @Size(min = 5, max = 100)
    private String passwordConfirm;

    public UserTo() {
    }

//    public UserTo(Integer id, String username, String password, String passwordConfirm) {
//        this(id, username, password, /*"", "",*/ passwordConfirm);
//    }

    public UserTo(Integer id, String username, String password/*, String firstName, String lastName*/) {
        this(id, username, password, /*firstName, lastName,*/ password);
    }

    public UserTo(Integer id, String username, String password, /*String firstName, String lastName,*/ String passwordConfirm) {
        this.id = id;
        this.username = username;
        this.password = password;
//        this.firstName = firstName;
//        this.lastName = lastName;
        if (!password.equals(passwordConfirm)) {
            System.out.println("constructor UserTo passwordConfirm is invalid");
            throw new ValidationException("password must by equals passwordConfirm");
        }
        this.passwordConfirm = passwordConfirm;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(@NotBlank @Size(min = 5, max = 100) String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username.trim().toLowerCase();
    }

//    public String getFirstName() {
//        return firstName;
//    }
//
//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//
//    public String getLastName() {
//        return lastName;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }

    @Override
    public String toString() {
        return "UserTo{" +
                "id=" + id +
                ", username='" + username + '\'' +
//                ", firstName='" + firstName + '\'' +
//                ", lastName='" + lastName + '\'' +
                '}';
    }
}
