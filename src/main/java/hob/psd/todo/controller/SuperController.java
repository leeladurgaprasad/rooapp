package hob.psd.todo.controller;

import hob.psd.todo.bean.Message;
import hob.psd.todo.bean.ResultMessages;
import org.springframework.ui.ModelMap;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

import static hob.psd.todo.constants.CommonConstants.RESULT_MESSAGES;

/**
 * Created by lgunti on 029, Nov 29.
 */
public class SuperController {

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
            resultMessages.getResultMessages().clear();
        }
    }

}
