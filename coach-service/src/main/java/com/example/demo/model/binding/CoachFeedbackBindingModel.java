package com.example.demo.model.binding;

import java.io.Serializable;

public class CoachFeedbackBindingModel implements Serializable {

    private String subjects;
    private String message;

    public CoachFeedbackBindingModel() {
    }

    public String getSubjects() {
        return subjects;
    }

    public CoachFeedbackBindingModel setSubjects(String subjects) {
        this.subjects = subjects;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public CoachFeedbackBindingModel setMessage(String message) {
        this.message = message;
        return this;
    }
}
