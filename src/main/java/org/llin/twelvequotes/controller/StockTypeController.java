package org.llin.twelvequotes.controller;

import org.llin.twelvequotes.model.AllQuotes;
import org.llin.twelvequotes.util.LoggingAdvice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/stockType")
public class StockTypeController extends Base {
	private static final Logger logger = LoggerFactory.getLogger(LoggingAdvice.class);
	public static final String ALL_QUOTES = "ALL_QUOTES";

	@GetMapping("/list")
	public ModelAndView getAllQuotes(HttpSession session) {
		ModelAndView modelAndView = new ModelAndView("stockType");
		
		modelAndView.addObject(ALL_QUOTES, (AllQuotes) session.getAttribute(ALL_QUOTES));	

		return modelAndView;
	}

	@PostMapping("/submit")
	public String handleFormSubmission(@RequestParam("selectedType") String selectedType,
											 @RequestParam("submit") String submit,
												                   HttpSession session) {
		String redirect = "redirect:/country/list";

		if (submit.equals("submit")) {
			redirect = "redirect:/quotes/list";
		}
		
		AllQuotes all_quotes = (AllQuotes) session.getAttribute(ALL_QUOTES);

		all_quotes.setSelectedType(selectedType); 
		all_quotes.populateForOnlySelectedType();
		session.setAttribute(ALL_QUOTES, all_quotes);

		return redirect; // Redirect to a success page
	}
	
	

}