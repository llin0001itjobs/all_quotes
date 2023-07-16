package org.llin.twelvequotes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/home")
public class HomeController {

    @GetMapping("/instruction")
    public ModelAndView showInstruction() {
		ModelAndView modelAndView = new ModelAndView("home");		
		return modelAndView; 
    }
    
	@PostMapping("/submit")
    public String handleFormSubmission(HttpSession session) {		
	    return "redirect:/country/list";    	    
    }    
		
}