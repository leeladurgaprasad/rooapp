package com.prasad.roostack.controller.exception;

import com.prasad.roostack.bean.Response;
import com.prasad.roostack.controller.SuperController;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

import static com.prasad.roostack.constants.CommonConstants.RESPONSE_MODEL;
import static com.prasad.roostack.constants.PageConstants.GLOBAL_JSON_VIEW;
import static com.prasad.roostack.constants.URLConstants.*;
import static javax.servlet.http.HttpServletResponse.*;
/**
 * Created by Leeladurga Prasad Gunti on 25-Apr-2015 17:10.
 */

@Controller
@RequestMapping(ERROR)
public class ExceptionController extends SuperController{

    @RequestMapping(method = {RequestMethod.GET},value = {"/{errorCode}"})
    public String processError(@ModelAttribute(RESPONSE_MODEL)Response<Object> objectResponse,
                               @PathVariable String errorCode,
                               HttpServletRequest request) {
        try {
            int errorCodeValue = Integer.parseInt(errorCode);
            objectResponse.setStatusCode(errorCodeValue);
            if(errorCodeValue == SC_INTERNAL_SERVER_ERROR) {
                resultMessages.errorResultMessage("error.500");
            } else if (errorCodeValue == SC_NOT_FOUND) {
                resultMessages.errorResultMessage("error.404");
            } else if (errorCodeValue == SC_UNAUTHORIZED) {
                resultMessages.errorResultMessage("error.401");
            }
        } catch (Exception e) {
            objectResponse.setStatusCode(SC_INTERNAL_SERVER_ERROR);
        }
        return GLOBAL_JSON_VIEW;
    }
}
