package com.prasad.roostack.bean;

import com.prasad.roostack.config.MessageConfig;
import com.prasad.roostack.constants.MessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lgunti on 029, Nov 29.
 */
@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ResultMessages {

    protected List<Message> resultMessages = new ArrayList<Message>();

    @Autowired
    private MessageConfig messageConfig;

    /**
     * Generic Method to add a message of own type and own content
     * @param messageType
     * @param messageContent
     */
    public void addResultMessage(String messageType, java.lang.String messageContent) {
        Message message = new Message();
        message.setType(messageType);
        message.setContent(messageConfig.getMessage(messageContent));
        resultMessages.add(message);
    }

    /**
     * Method to add success message to result messages
     * @param messageContent
     * @param args
     */
    public void successResultMessage(java.lang.String messageContent,Object... args) {
        Message message = new Message();
        message.setType(MessageType.SUCCESS.toString());
        message.setContent(messageConfig.getMessage(messageContent,args));
        resultMessages.add(message);
    }

    /**
     * Method to add warning message to result messages
     * @param messageContent
     * @param args
     */
    public void warnResultMessage(java.lang.String messageContent,Object... args) {
        Message message = new Message();
        message.setType(MessageType.WARN.toString());
        message.setContent(messageConfig.getMessage(messageContent,args));
        resultMessages.add(message);
    }

    /**
     * Method to add info message to result messages
     * @param messageContent
     * @param args
     */
    public void infoResultMessage(java.lang.String messageContent,Object... args) {
        Message message = new Message();
        message.setType(MessageType.INFO.toString());
        message.setContent(messageConfig.getMessage(messageContent,args));
        resultMessages.add(message);
    }

    /**
     * Method to add error message to result messages
     * @param messageContent
     * @param args
     */
    public void errorResultMessage(java.lang.String messageContent,Object... args) {
        Message message = new Message();
        message.setType(MessageType.ERROR.toString());
        message.setContent(messageConfig.getMessage(messageContent,args));
        resultMessages.add(message);
    }


    public List<Message> getResultMessages() {
        return resultMessages;
    }

    public void setResultMessages(List<Message> resultMessages) {
        this.resultMessages = resultMessages;
    }
}
