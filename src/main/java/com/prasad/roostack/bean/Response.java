package com.prasad.roostack.bean;

import com.prasad.roostack.bean.Message;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Leeladurga Prasad Gunti on 06-Apr-2015 10:37.
 */
public class Response<ResponseObject> implements Serializable {

    private static final long serialVersionUID = -1790475271223756527L;

    /* Status code of response */
    private int statusCode;

    private ResponseObject responseObject;

    private List<Message> messages = new ArrayList<Message>();

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public ResponseObject getResponseObject() {
        return responseObject;
    }

    public void setResponseObject(ResponseObject responseObject) {
        this.responseObject = responseObject;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}
