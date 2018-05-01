package com.prasad.roostack.controller.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.prasad.roostack.bean.Message;
import com.prasad.roostack.bean.ResultMessages;
import com.prasad.roostack.bean.User;
import com.prasad.roostack.bean.form.SignInForm;
import com.prasad.roostack.constants.MessageType;
import com.prasad.roostack.constants.ResponseCodes;
import com.prasad.roostack.controller.SuperController;
import com.prasad.roostack.manager.UserManager;
import com.prasad.roostack.mapper.config.SignInFormUserMapper;
import com.prasad.roostack.bean.Response;
import com.prasad.roostack.util.web.ControlUtil;
import com.prasad.roostack.util.web.CookieUtil;
import com.prasad.roostack.validators.SignInFormValidator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.awt.*;
import java.io.PrintWriter;
import java.io.StringWriter;

import static com.prasad.roostack.constants.PageConstants.*;
import static com.prasad.roostack.constants.FormConstants.*;
import static com.prasad.roostack.constants.URLConstants.*;
import static com.prasad.roostack.constants.CommonConstants.*;
import static com.prasad.roostack.util.web.CookieUtil.*;

import static javax.servlet.http.HttpServletResponse.*;


@Controller
@RequestMapping(SIGN_IN)
public class SignInController extends SuperController{

    private final Logger logger = LoggerFactory.getLogger(SignInController.class);


    @RequestMapping(method = {RequestMethod.POST,RequestMethod.GET}, value = {"", "*"})
    public String processSignInForm(@ModelAttribute(SIGN_IN_FORM) SignInForm signInForm,ModelMap modelMap,
                                    @ModelAttribute(RESPONSE_MODEL)Response<Object> objectResponse,
                                    HttpServletRequest request) {

        logger.debug("Start :: Sign In Controller POST");
        logger.debug("Sign In Controller username : " + signInForm.getUserName() + " Keep me sign in : " + signInForm.isKeepMeSignIn());

        User originalUser = null;
        try {
            if(signInForm.getUserName() != null) {

                originalUser = userManager.getUserByUserName(signInForm.getUserName());

                if(null != originalUser && originalUser.getPassword().equals(signInForm.getPassword())) {
                    String authorizationKey = ControlUtil.generateToken();
                    originalUser.setAccessKey(authorizationKey);
                    userManager.updateUser(originalUser);
                    objectResponse.setStatusCode(SC_OK);
                    objectResponse.setResponseObject(authorizationKey);
                } else {
                    if(null == originalUser) {
                        resultMessages.errorResultMessage("input.user.invalid");
                    } else {
                        resultMessages.errorResultMessage("input.user.password.invalid");
                    }
                    objectResponse.setStatusCode(SC_UNAUTHORIZED);
                }
            }
        } catch (Exception e) {
            objectResponse.setStatusCode(SC_INTERNAL_SERVER_ERROR);
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            Message message = new Message(MessageType.ERROR.toString(),"Exception occured "+ exceptionAsString);
            resultMessages.getResultMessages().add(message);
        }

        return GLOBAL_JSON_VIEW;

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
