package hob.psd.todo.controller.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import hob.psd.todo.bean.User;
import hob.psd.todo.bean.form.SignInForm;
import hob.psd.todo.controller.SuperController;
import hob.psd.todo.manager.UserManager;
import hob.psd.todo.mapper.config.SignInFormUserMapper;
import hob.psd.todo.util.web.ControlUtil;
import hob.psd.todo.util.web.CookieUtil;
import hob.psd.todo.validators.SignInFormValidator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.net.URLEncoder;

import static hob.psd.todo.constants.PageConstants.*;
import static hob.psd.todo.constants.FormConstants.*;
import static hob.psd.todo.constants.URLConstants.*;
import static hob.psd.todo.constants.CommonConstants.*;
import static hob.psd.todo.util.web.CookieUtil.*;


@Controller
@RequestMapping(SIGN_IN)
public class SignInController extends SuperController{

    private final Logger logger = LoggerFactory.getLogger(SignInController.class);

    @RequestMapping(method = {RequestMethod.GET}, value = {"", "*"})
    public String showForm(@ModelAttribute(SIGN_IN_FORM) SignInForm signInForm, ModelMap modelMap, HttpServletRequest request) throws Exception{
        logger.debug("Start :: Sign In Controller GET");

        if(null != request.getQueryString()) {
            String queryString = request.getQueryString();
        }
        if (!LOGIN_USER_PAGE.equalsIgnoreCase(signInForm.getFromPage())) {
            if (ControlUtil.isEligibleForLoginUser(request)) {
                String userNameFromCookie = CookieUtil.getUserFromCookie(request);
                User user = userManager.getUserByUserId(Integer.parseInt(userNameFromCookie));
                if(user != null) {
                    signInForm.setUserName(user.getUserName());
                    signInForm.setUserId(user.getUserId());
                    return LOGIN_USER_PAGE;
                }
            }
        }
        return LOGIN_PAGE;
    }

    @RequestMapping(method = {RequestMethod.GET}, value = {"/loginuser"})
    public String showLoginUserForm(@ModelAttribute(SIGN_IN_FORM) SignInForm signInForm, ModelMap modelMap, HttpServletRequest request,HttpServletResponse response) throws Exception{

        logger.debug("Start :: Sign In Controller GET");
        String userNameFromCookie = CookieUtil.getUserFromCookie(request);
        User user = userManager.getUserByUserId(Integer.parseInt(userNameFromCookie));

        if(user != null) {
            signInForm.setUserName(user.getUserName());
            signInForm.setUserId(user.getUserId());
            return LOGIN_USER_PAGE;
        }

        return LOGIN_PAGE;
    }


    @RequestMapping(method = {RequestMethod.POST}, value = {"", "*"})
    public String processSignInForm(@ModelAttribute(SIGN_IN_FORM) SignInForm signInForm, BindingResult result,
                                    HttpServletRequest request, HttpServletResponse response) {

        logger.debug("Start :: Sign In Controller POST");
        logger.debug("Sign In Controller username : " + signInForm.getUserName() + " Keep me sign in : " + signInForm.isKeepMeSignIn());


        signInFormValidator.validate(signInForm, result);

        if (result.hasErrors()) {
            return LOGIN_PAGE;
        }

        boolean loginSuccess = false;
        User user = ControlUtil.buildUser(signInForm);
        User originalUser = null;
        if (null != user.getUserName()) {
            originalUser = userManager.getUserByUserName(user.getUserName());
        } else if (null != user.getEmail()) {
            originalUser = userManager.getUserByEmail(user.getEmail());
        }

        if (null == originalUser) {
            signInForm.setUserId(user.getUserId());
            result.rejectValue("userName", "invalid");
            return LOGIN_PAGE;
        }

        loginSuccess = user.getPassword().equalsIgnoreCase(originalUser.getPassword());

        if (loginSuccess) {

            logger.debug("User " + (user.getUserName() != null ? user.getUserName() : user.getEmail()) + " login success");

            user = originalUser;
            if ((LOGIN_PAGE.equalsIgnoreCase(signInForm.getFromPage()) && !signInForm.isKeepMeSignIn()
                    && ! user.getUserName().equalsIgnoreCase(getUserFromCookie(request)))) {
                createCookie(response, LOGIN_USER, "false");
            } else {
                createCookie(response, LOGIN_USER, "true");
            }
            createUserCookie(response,Integer.toString(user.getUserId()));
            HttpSession session = request.getSession(true);
            session.setAttribute( SESSION_USER , user );
            if(null != signInForm.getContinuePage()) {
                request.setAttribute("targetUrl",signInForm.getContinuePage());
            }
            return "SuccessView";
        } else {
            logger.debug("User " + (user.getUserName() != null ? user.getUserName() : user.getEmail()) + " login failed");
            signInForm.setUserId(originalUser.getUserId());
            result.reject("failed.login");
        }

        return LOGIN_PAGE;
    }

    @Autowired
    private SignInFormValidator signInFormValidator;

    @Autowired
    private UserManager userManager;

    @Autowired
    private ControlUtil controlUtil;

    @Autowired
    private SignInFormUserMapper signInFormUserMapper;
}
