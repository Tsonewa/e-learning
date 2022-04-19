package com.example.demo.model.entity;


import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "resources")
public class ResourceEntity extends BaseEntity{

    private String title;
    private String filePath;

    public ResourceEntity() {
    }

    public String getTitle() {
        return title;
    }

    public ResourceEntity setTitle(String title) {
        this.title = title;
        return this;
    }


    public String getFilePath() {
        return filePath;
    }

    public ResourceEntity setFilePath(String filePath) {
        this.filePath = filePath;
        return this;
    }
}
