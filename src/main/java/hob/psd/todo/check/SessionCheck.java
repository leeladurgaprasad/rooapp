package hob.psd.todo.check;

import static hob.psd.todo.constants.CommonConstants.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.URLEncoder;

/**
 * Created by lgunti on 018, Dec 18.
 */
public class SessionCheck extends NavigationCheck {
    private final Logger logger = LoggerFactory.getLogger(SessionCheck.class);

    @Override
    public boolean check(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null || !request.isRequestedSessionIdValid() || null == session.getAttribute(SESSION_USER)) {
            if(null != exitUrl) {
                try {
                    if(null == request.getAttribute(QUERY_PARAM)) {
                        String queryParam = QUESTION_MARK + "continuePage" + EQUALS + URLEncoder.encode(request.getRequestURL().toString(), "UTF-8");
                        request.setAttribute(QUERY_PARAM,queryParam);
                    }
                } catch (Exception ex) {
                    logger.error("unable to encode url "+request.getRequestURL(),ex);
                }
            }
            logger.debug("session check failed");
            return false;
        }
        logger.debug("session check passed");
        return true;
    }
}
