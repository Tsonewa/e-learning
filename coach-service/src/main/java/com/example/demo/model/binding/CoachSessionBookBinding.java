package com.example.demo.model.binding;

import java.io.Serializable;

public class CoachSessionBookBinding implements Serializable {

    private String idCoach;
    private String id;

    public CoachSessionBookBinding() {
    }

    public String getIdCoach() {
        return idCoach;
    }

    public CoachSessionBookBinding setIdCoach(String idCoach) {
        this.idCoach = idCoach;
        return this;
    }

    public String getId() {
        return id;
    }

    public CoachSessionBookBinding setId(String id) {
        this.id = id;
        return this;
    }
}
