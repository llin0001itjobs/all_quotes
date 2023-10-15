package org.llin.twelvequotes.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class CustomErrorController implements ErrorController {
	private static final String RESPONSE_ENTITY = "RESPONSE_ENTITY";
	
	@GetMapping("/400")
	public ModelAndView handle400() {
		ModelAndView mv = new ModelAndView("400");		
		mv.addObject(RESPONSE_ENTITY, new ResponseEntity<>(HttpStatus.BAD_REQUEST));
		return mv;
	}
	
	@GetMapping("/500")
	public ModelAndView handle500() {
		ModelAndView mv = new ModelAndView("500");
		mv.addObject(RESPONSE_ENTITY, new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
		return mv;
	}
	
	@GetMapping("/error")
	public ModelAndView handleError() {
		Integer errorCode = getErrorCode();
		
		ResponseEntity<String> response = ResponseEntity.status(errorCode).build();
		int statusCode = response.getStatusCode().value();
		String page = "500";

		if (statusCode >= HttpStatus.BAD_REQUEST.value() && statusCode < HttpStatus.INTERNAL_SERVER_ERROR.value()) {
			page = "400";
		}
		if (statusCode >= HttpStatus.INTERNAL_SERVER_ERROR.value()) {
			page = "500";
		}
				
		ModelAndView mv = new ModelAndView(page);
		mv.addObject("RESPONSE_ENTITY", response);
		return mv;
	}
	
    private Integer getErrorCode() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Integer code = (Integer) request.getAttribute("javax.servlet.error.status_code");  
        return  code == null ? HttpStatus.OK.value() : code;     
    }
	
}
