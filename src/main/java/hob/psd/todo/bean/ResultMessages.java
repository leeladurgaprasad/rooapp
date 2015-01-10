package hob.psd.todo.bean;

import hob.psd.todo.config.MessageConfig;
import hob.psd.todo.constants.MessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lgunti on 029, Nov 29.
 */
@Component
public class ResultMessages {

    protected List<Message> resultMessages = new ArrayList<Message>();

    @Autowired
    private MessageConfig messageConfig;

    /**
     * Generic Method to add a message of own type and own content
     * @param messageType
     * @param messageContent
     */
    public void addResultMessage(MessageType messageType, String messageContent) {
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
    public void successResultMessage(String messageContent,Object... args) {
        Message message = new Message();
        message.setType(MessageType.SUCCESS);
        message.setContent(messageConfig.getMessage(messageContent,args));
        resultMessages.add(message);
    }

    /**
     * Method to add warning message to result messages
     * @param messageContent
     * @param args
     */
    public void warnResultMessage(String messageContent,Object... args) {
        Message message = new Message();
        message.setType(MessageType.WARN);
        message.setContent(messageConfig.getMessage(messageContent,args));
        resultMessages.add(message);
    }

    /**
     * Method to add info message to result messages
     * @param messageContent
     * @param args
     */
    public void infoResultMessage(String messageContent,Object... args) {
        Message message = new Message();
        message.setType(MessageType.INFO);
        message.setContent(messageConfig.getMessage(messageContent,args));
        resultMessages.add(message);
    }

    /**
     * Method to add error message to result messages
     * @param messageContent
     * @param args
     */
    public void errorResultMessage(String messageContent,Object... args) {
        Message message = new Message();
        message.setType(MessageType.ERROR);
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
