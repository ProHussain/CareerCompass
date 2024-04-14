package com.hashmac.careercompass.beans;

import com.google.firebase.Timestamp;

public class Notification {
    private String id;
    private String title;
    private String message;
    private String actionURL;
    private Timestamp date;

    public Notification() {
    }

    public Notification(String id, String title, String message, String actionURL, Timestamp date) {
        this.id = id;
        this.title = title;
        this.message = message;
        this.actionURL = actionURL;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public String getActionURL() {
        return actionURL;
    }

    public Timestamp getDate() {
        return date;
    }
}
