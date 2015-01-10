package hob.psd.todo.bean;

import hob.psd.todo.constants.MessageType;

/**
 * Created by lgunti on 029, Nov 29.
 */
public class Message {
    private MessageType type;
    private String content;

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
