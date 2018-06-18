package com.example.sean.dingdang_and;

import org.litepal.crud.DataSupport;

public class Message extends DataSupport{
    private int id;
    private String message;
    private Long time;
    private String type;

    public Message(){

    }

    public Message(String message, Long time, String type) {
        this.message = message;
        this.time = time;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
