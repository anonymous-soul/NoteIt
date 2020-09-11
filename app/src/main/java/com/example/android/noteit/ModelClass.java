package com.example.android.noteit;

import java.io.Serializable;

public class ModelClass implements Serializable {
    private String title;
    private String description;
    private int id;

    public ModelClass() {
    }

    public ModelClass(int id,String title,String description) {
        this.title = title;
        this.id=id;
        this.description = description;
    }

    public ModelClass(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
