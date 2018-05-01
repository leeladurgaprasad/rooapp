package com.prasad.roostack.validators;

import com.prasad.roostack.bean.form.SignInForm;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class SignInFormValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		return SignInFormValidator.class.isAssignableFrom(clazz);
	}

	public void validate(Object object, Errors errors) {
		
		if(null == object) {
			return;
		}
		
		if( !(object instanceof SignInForm) ) {
			return;
		}
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "required");
		//errors.reject("invalid.input");

	}

}
