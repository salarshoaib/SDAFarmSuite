package com.example.businesslayer;

public class Message {
    private String date;
    private String sender;
    private String message;
    private String status;

    public Message(String date, String sender, String message, String status) {
        this.date = date;
        this.sender = sender;
        this.message = message;
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}