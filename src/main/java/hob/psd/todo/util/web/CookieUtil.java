package hob.psd.todo.util.web;

import org.apache.commons.lang.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Component;

import static hob.psd.todo.constants.CommonConstants.*;

/**
 * Created by lgunti on 015, Nov 15.
 */
@Component
public class CookieUtil {

    public static void createUserCookie(HttpServletResponse response, String userName) {
        byte[] encodedUserBytes = Base64.encodeBase64(userName.getBytes());
        String encodedUserString = new String(encodedUserBytes);
        createCookie(response,USER_COOKIE, encodedUserString);
    }

    public static String getUserFromCookie(HttpServletRequest request) {
        String decodedUserString = null;
        Cookie userCookie = getCookie(request,USER_COOKIE);

        if(null != userCookie) {
            String encodedUserString = getCookie(request, USER_COOKIE).getValue();
            byte[] encodedUserBytes = encodedUserString.getBytes();
            byte[] decodedUserBytes = Base64.decodeBase64(encodedUserBytes);
            decodedUserString = new String(decodedUserBytes);
        }
        return decodedUserString;
    }

    public static void createCookie(HttpServletResponse response,String cookieName, String cookeValue) {
        if(StringUtils.isNotEmpty(cookieName) && StringUtils.isNotEmpty(cookeValue)) {
            response.addCookie(new Cookie(cookieName,cookeValue));
        }
    }

    public static Cookie getCookie(HttpServletRequest request, String cookieName) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                if (cookies[i].getName().equals(cookieName)) {
                    return cookies[i];
                }
            }
        }
        return null;
    }
}
