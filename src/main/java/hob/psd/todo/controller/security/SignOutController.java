package hob.psd.todo.controller.security;

import hob.psd.todo.constants.CommonConstants;
import hob.psd.todo.controller.SuperController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static hob.psd.todo.constants.URLConstants.*;


@Controller
@RequestMapping(SIGN_OUT)
public class SignOutController extends SuperController{

    @RequestMapping(method = {RequestMethod.GET},value = {"","*"})
    public String processSignOut(HttpServletRequest request,HttpServletResponse response, HttpSession session){
        session.removeAttribute(CommonConstants.SESSION_USER);
        session.invalidate();
        return "LoginUserPageRedirect";
    }
}
