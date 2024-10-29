package com.example.guitarmobileapp;

public class ProfileList {
    private String description;
    private String changes;

    public ProfileList(String description, String changes) {
        this.description = description;
        this.changes = changes;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getChanges() {
        return changes;
    }

    public void setChanges(String changes) {
        this.changes = changes;
    }
}
