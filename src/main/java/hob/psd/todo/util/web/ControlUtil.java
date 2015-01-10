package hob.psd.todo.util.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import hob.psd.todo.constants.LevelConstants;
import static hob.psd.todo.constants.CommonConstants.*;
import hob.psd.todo.bean.User;
import hob.psd.todo.bean.form.SignInForm;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;


@Component
public class ControlUtil {

	public static User buildUser(SignInForm signInForm) {
		User user = new User();
        if(isEmail(signInForm.getUserName())) {
            user.setEmail(signInForm.getUserName());
        } else {
            user.setUserName(signInForm.getUserName());
        }
		user.setPassword(signInForm.getPassword());
		return user;
	}
	
	public static List<String> getLevelsList(){
		List<String> levelsList = new ArrayList<String>();
		for (LevelConstants levelConstants : LevelConstants.values()) {
			levelsList.add(levelConstants.getDescription());
		}
		return levelsList;
	}

    public static boolean isEmail(String email) {
        if(StringUtils.isNotEmpty(email)) {
            if(email.contains("@")) {
                return true;
            }
        }
        return false;
    }

    public static boolean isEligibleForLoginUser(HttpServletRequest request) {
        Cookie loginUserCookie = CookieUtil.getCookie(request,LOGIN_USER);
        String loginUserCookieValue = null;

        if(null != loginUserCookie) {

            // if login user cookie found then check value
            loginUserCookieValue = loginUserCookie.getValue();
            if(Boolean.valueOf(loginUserCookieValue)) {
                return true;
            }

        } else {
            // if login user cookie not found then check user cookie
            String userName = CookieUtil.getUserFromCookie(request);
            if(StringUtils.isNotEmpty(userName)) {
                return true;
            }
        }

        return false;

    }


    public static String getTodayDate(){
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DEFAULT_DATE_PATTEN);
        return simpleDateFormat.format(date);
    }

    public static List<String> getUserNamesList(List<User> users) {
        List<String> userNames = null;
        if(!users.isEmpty()) {
            userNames = new ArrayList<String>();
            for(User user: users) {
                  userNames.add(user.getUserName());
            }
        }
        return userNames;
    }

    public static LinkedHashMap<Integer,String> getUsersLinkedHashMap(List<User> users) {
        LinkedHashMap<Integer,String> usersMap = null;
        if(! users.isEmpty()) {
            usersMap = new LinkedHashMap<Integer, String>();
            for(User user : users) {
                usersMap.put(user.getUserId(),user.getUserName());
            }
        }

        return usersMap;
    }
}
