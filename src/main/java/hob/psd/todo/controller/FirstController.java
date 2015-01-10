package hob.psd.todo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import static hob.psd.todo.constants.PageConstants.*;

@Controller
@RequestMapping("/First")
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

}
