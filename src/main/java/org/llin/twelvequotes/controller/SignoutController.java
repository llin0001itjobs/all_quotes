package org.llin.twelvequotes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/signOut")
public class SignoutController {

	@PostMapping("/submit")
    public String handleFormSubmission(HttpSession session) {
		session.invalidate();		
	    return "signOut";    	    
    }    
}
