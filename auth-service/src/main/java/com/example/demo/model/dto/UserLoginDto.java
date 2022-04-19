package com.example.demo.model.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class UserLoginDto implements Serializable {

    private String email;
    private String password;

    public UserLoginDto() {
    }

    @Email
    @NotNull(message = "Email can not be empty")
    public String getEmail() {
        return email;
    }

    public UserLoginDto setEmail(String email) {
        this.email = email;
        return this;
    }

    @Length(min = 8, max=8, message = "Password must contains 8 symbols")
    @NotNull(message = "Password can not be empty")
    public String getPassword() {
        return password;
    }

    public UserLoginDto setPassword(String password) {
        this.password = password;
        return this;
    }
}
