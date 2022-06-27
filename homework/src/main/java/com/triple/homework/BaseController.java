package com.triple.homework;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BaseController implements ErrorController{

	private static final String frontUrl = "/frontend/public";
	

	@RequestMapping(value="/")
	public String root() {
		return "index.html";
	}
	
	@RequestMapping({"/", "/error"})
    public String index() {
		return "index.html";
    }
	
	
	

}
