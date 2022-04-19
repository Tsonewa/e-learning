package com.example.demo.model.binding;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class ResourceBindingModel implements Serializable {

    private String title;
    private String filePath;

    public ResourceBindingModel() {
    }

    @Length(min = 3, max = 5000, message = "Please provide info between 3-5000 chars")
    @NotNull(message = "Please provide info")
    public String getTitle() {
        return title;
    }

    public ResourceBindingModel setTitle(String title) {
        this.title = title;
        return this;
    }

    @NotNull(message = "Provide info")
    public String getFilePath() {
        return filePath;
    }

    public ResourceBindingModel setFilePath(String filePath) {
        this.filePath = filePath;
        return this;
    }
}
