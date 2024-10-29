package com.example.guitarmobileapp;

public class LearnList {
    private String title;
    private String description;
    private boolean mirror;

    public LearnList(String title, String description, boolean mirror) {
        this.title = title;
        this.description = description;
        this.mirror = mirror;
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

    public boolean getMirror() {
        return mirror;
    }

    public void setMirror(boolean mirror) {
        this.mirror = mirror;
    }
}
