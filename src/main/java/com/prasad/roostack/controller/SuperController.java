package com.prasad.roostack.controller;

import com.prasad.roostack.bean.Message;
import com.prasad.roostack.bean.Response;
import com.prasad.roostack.bean.ResultMessages;
import com.prasad.roostack.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

import static com.prasad.roostack.constants.CommonConstants.*;

/**
 * Created by lgunti on 029, Nov 29.
 */
public class SuperController {

    @ModelAttribute(RESPONSE_MODEL)
    public Response<Object> buildResponse(ModelMap map) {
        Response<Object> response = new Response<Object>();
        response.setMessages(resultMessages.getResultMessages());
        map.put(RESPONSE_KEY,response);
        return response;
    }

    @ModelAttribute(USER_MODEL)
    public User buildResponse(HttpServletRequest request) {
        User user = (User) request.getAttribute(USER_KEY);
        return user;
    }

    protected void showResultMessages(HttpSession session,ModelMap modelMap) {
        // get the result messages in session to show in status of previous post
        List<Message> sessionResultMessages = (List<Message>) session.getAttribute(RESULT_MESSAGES);
        if(null != sessionResultMessages) {
            List allResultMessages = new ArrayList(sessionResultMessages);
            modelMap.put(RESULT_MESSAGES, allResultMessages);
            sessionResultMessages.clear();
            // remove messages from session so that message will not be shown for next message
            session.removeAttribute(RESULT_MESSAGES);
        }
    }

    protected void showResultMessages(ResultMessages resultMessages,ModelMap modelMap) {
        if(null!= resultMessages) {
            List allResultMessages = new ArrayList(resultMessages.getResultMessages());
            modelMap.put(RESULT_MESSAGES,allResultMessages);
            //resultMessages.getResultMessages().clear();
        }
    }

    @Autowired
    @Qualifier("resultMessagesBean")
    protected ResultMessages resultMessages;

}
