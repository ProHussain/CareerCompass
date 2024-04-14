package com.hashmac.careercompass.beans.chat;

public class Chat {
    private String id;
    private String message;
    private int type; // 0 for user, 1 for AI bot

    private String date;

    public Chat(String id, String message, int type, String date) {
        this.id = id;
        this.message = message;
        this.type = type;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public int getType() {
        return type;
    }

    public String getDate() {
        return date;
    }
}
