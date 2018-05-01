package com.prasad.roostack.check;

import com.prasad.roostack.bean.User;
import com.prasad.roostack.manager.UserManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import static com.prasad.roostack.constants.CommonConstants.*;

/**
 * Created by Leeladurga Prasad Gunti on 25-Apr-2015 16:15.
 */

@Component
public class AccessKeyCheck extends NavigationCheck {

    private final Logger logger = LoggerFactory.getLogger(AccessKeyCheck.class);
    @Override
    public boolean check(HttpServletRequest request) {
        String accessKey = request.getParameter(ACCESS_KEY);
        if(null != accessKey) {
            User user = userManager.getUserByAccessKey(accessKey);
            if (null != user) {
                request.setAttribute(USER_KEY,user);
                return true;
            }
        }
        return false;
    }


    @Autowired
    private UserManager userManager;
}
