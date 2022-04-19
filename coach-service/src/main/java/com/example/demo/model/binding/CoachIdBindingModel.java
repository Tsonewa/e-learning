package com.example.demo.model.binding;

import java.io.Serializable;

public class CoachIdBindingModel implements Serializable {

    private String id;

    public CoachIdBindingModel() {
    }

    public String getId() {
        return id;
    }

    public CoachIdBindingModel setId(String id) {
        this.id = id;
        return this;
    }
}
