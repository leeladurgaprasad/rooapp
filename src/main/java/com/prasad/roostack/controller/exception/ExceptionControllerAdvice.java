package com.prasad.roostack.controller.exception;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static com.prasad.roostack.constants.PageConstants.*;

@Controller
public class ExceptionControllerAdvice {

	@ExceptionHandler
	public String handleException(Exception e) {
		return GENERIC_ERROR_PAGE;
	}
	
	@ExceptionHandler
	public String handleAssertion(org.hibernate.AssertionFailure a){
		return GENERIC_ERROR_PAGE;
	}
}
