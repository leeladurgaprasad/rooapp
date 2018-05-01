package com.prasad.roostack.bean;


/**
 * Created by lgunti on 029, Nov 29.
 */
public class Message {
    private String type;
    private String content;

    Message(){}

    public Message(String type, String content) {
        this.type = type;
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
