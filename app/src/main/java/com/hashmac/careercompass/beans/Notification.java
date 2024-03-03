package com.hashmac.careercompass.beans;

public class Notification {
    private String id;
    private String title;
    private String message;
    private Long date;

    public Notification() {
    }

    public Notification(String id, String title, String message, Long date) {
        this.id = id;
        this.title = title;
        this.message = message;
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

    public Long getDate() {
        return date;
    }
}
