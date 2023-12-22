package org.llin.twelvequotes.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class CustomErrorController implements ErrorController {
	private static final String RESPONSE_ENTITY = "RESPONSE_ENTITY";
	
	@Autowired
	private ErrorAttributes errorAttributes;
	
	@RequestMapping("/400")
	public ModelAndView handle400() {
		ModelAndView mv = new ModelAndView("400");		
		mv.addObject(RESPONSE_ENTITY, new ResponseEntity<>(HttpStatus.BAD_REQUEST));
		return mv;
	}
	
	@RequestMapping("/500")
	public ModelAndView handle500() {
		ModelAndView mv = new ModelAndView("500");
		mv.addObject(RESPONSE_ENTITY, new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
		return mv;
	}
	
	@RequestMapping("/error")
	public ModelAndView handleError(HttpServletRequest request) {
	    Map<String, Object> errorAttributesMap = errorAttributes.getErrorAttributes(new ServletWebRequest(request),  ErrorAttributeOptions.defaults());
	    	    
	    int errorCode = (int) errorAttributesMap.get("status");
	    	    
		ResponseEntity<String> response = ResponseEntity.status(errorCode).build();
		
		String page = "500";

		if (errorCode >= HttpStatus.BAD_REQUEST.value() && errorCode < HttpStatus.INTERNAL_SERVER_ERROR.value()) {
			page = "400";
		}
		if (errorCode >= HttpStatus.INTERNAL_SERVER_ERROR.value()) {
			page = "500";
		}
				
		ModelAndView mv = new ModelAndView(page);
		if (response.hasBody()) {
			mv.addObject(RESPONSE_ENTITY, response.getBody());
		}
		return mv;
	}

	
}
