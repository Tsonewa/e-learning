package com.example.demo.model.binding;

import java.util.ArrayList;
import java.util.List;

public class ResourcesBindingList {

    private List<ResourceBindingModel> resources;

    public ResourcesBindingList() {
        this.resources = new ArrayList<>();
    }

    public List<ResourceBindingModel> getResources() {
        return resources;
    }

    public ResourcesBindingList setResources(List<ResourceBindingModel> resources) {
        this.resources = resources;
        return this;
    }

}
