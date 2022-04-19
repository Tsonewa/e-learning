package com.example.demo.model.binding;

import org.apache.kafka.common.serialization.Deserializer;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class UserProfileCreateBindingModel implements Serializable {

    private String companyName;
    private String email;
    private String fullName;

    @NotNull(message = "Please provide information")
    @Length(min = 3,message = "Please enter minimum 3 characters")
    public String getFullName() {
        return fullName;
    }

    public UserProfileCreateBindingModel setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public UserProfileCreateBindingModel() {
    }

    @NotNull(message = "Please provide information.")
    public String getCompanyName() {
        return companyName;
    }

    public UserProfileCreateBindingModel setCompanyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    @NotNull(message = "Please provide information.")
    @Email(message = "Please provide email.")
    public String getEmail() {
        return email;
    }

    public UserProfileCreateBindingModel setEmail(String email) {
        this.email = email;
        return this;
    }
}
