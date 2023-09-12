package com.example.thechamber.utilities;
public class ChatMessage {
    private String username;
    private String message;
    private String timestamp;

    public ChatMessage(String username, String message, String timestamp) {
        this.username = username;
        this.message = message;
        this.timestamp = timestamp;
    }

    public String getUsername() {
        return username;
    }

    public String getMessage() {
        return message;
    }

    public String getTimestamp() {
        return timestamp;
    }
}
