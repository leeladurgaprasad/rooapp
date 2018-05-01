package com.prasad.roostack.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import static com.prasad.roostack.constants.PageConstants.*;

@Controller
@RequestMapping("/data")
public class FirstController {
	
	private final Logger logger = LoggerFactory.getLogger(FirstController.class);
	
	public FirstController() {
		logger.info("First Controller Init");
	}
	
	@RequestMapping(method={RequestMethod.GET},value={"","*","/show"})
	public String showForm(HttpServletRequest request,HttpServletResponse responce) {
		logger.info("First Controller GET");
		return WELCOME_PAGE;
	}

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET,RequestMethod.POST},value = {"/form"})
    public String dataFromView(HttpServletRequest request,HttpServletResponse response) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println(" u : "+username+" p : "+password);
        return "Bang Ok..";
    }


}
