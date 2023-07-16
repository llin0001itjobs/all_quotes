package org.llin.twelvequotes.controller;

import java.io.IOException;

import org.llin.twelvequotes.model.AllQuotes;
import org.llin.twelvequotes.model.SingleQuote;
import org.llin.twelvequotes.util.JsonUtil;
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
@RequestMapping("/country")
public class CountryController {
	private static final Logger logger = LoggerFactory.getLogger(LoggingAdvice.class);
	public static final String ALL_QUOTES = "ALL_QUOTES";

	@GetMapping("/list")
	public ModelAndView getAllQuotes(HttpSession session) {
		ModelAndView modelAndView = new ModelAndView("country");

		try {
			if (session.getAttribute(ALL_QUOTES) == null) {
				JsonUtil<SingleQuote> jsonUtil = new JsonUtil<>("https://api.twelvedata.com/stocks");
				AllQuotes all_quotes = new AllQuotes(jsonUtil.retrieveObject());
				all_quotes.populateCountrySet();
				session.setAttribute(ALL_QUOTES, all_quotes);
			}
			modelAndView.addObject(ALL_QUOTES, (AllQuotes) session.getAttribute(ALL_QUOTES));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return modelAndView;
	}

	@PostMapping("/submit")
	public String handleFormSubmission(@RequestParam("selectedCountry") String selectedCountry,
												@RequestParam("submit") String submit,
												                   HttpSession session) {
		String redirect = "redirect:/country/list";

		if (submit.equals("submit")) {
			redirect = "redirect:/stockType/list";
		}
		
		AllQuotes all_quotes = (AllQuotes) session.getAttribute(ALL_QUOTES);

		all_quotes.setSelectedCountry(selectedCountry);
		all_quotes.populateForOnlySelectedCountry();
		all_quotes.populateTypeSet();
		
		session.setAttribute(ALL_QUOTES, all_quotes);

		return redirect; // Redirect to a success page
	}
	
	

}