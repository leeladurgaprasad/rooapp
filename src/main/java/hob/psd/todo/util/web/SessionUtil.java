package hob.psd.todo.util.web;

import hob.psd.todo.bean.User;
import hob.psd.todo.constants.CommonConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by lgunti on 020, Dec 20.
 */
@Component
public class SessionUtil {

    public static User getUser(HttpServletRequest request) {
        User user = null;
        HttpSession session = request.getSession(false);
        if(session != null && request.isRequestedSessionIdValid()) {
            user = (User) session.getAttribute(CommonConstants.SESSION_USER);
        }
        return user;
    }

}
