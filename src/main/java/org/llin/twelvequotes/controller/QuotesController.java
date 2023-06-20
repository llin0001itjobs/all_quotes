package org.llin.twelvequotes.controller;

import java.io.IOException;

import org.llin.twelvequotes.controller.util.JsonUtil;
import org.llin.twelvequotes.model.AllQuotes;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/quotes")
public class QuotesController {
	
	private static final String QUOTES = "QUOTES";
	private static final String SELECT_COUNTRY = "SELECT_COUNTRY";
	private static final String SELECT_TYPE = "SELECT_TYPE";	
	private static final String THYMELEAF_TEMPLATE = "quotes"; 
	
	@GetMapping("/list")	
	public ModelAndView getAllQuotes() {
		ModelAndView modelAndView = new ModelAndView(THYMELEAF_TEMPLATE);
		
		try {
			JsonUtil jsonUtil = new JsonUtil();
			AllQuotes all_quotes = new AllQuotes(jsonUtil.retrieveObject());
			all_quotes.populateSets();
			
			modelAndView.addObject(SELECT_COUNTRY, all_quotes.getCountrySet());
			modelAndView.addObject(SELECT_TYPE, all_quotes.getTypeSet());
			modelAndView.addObject(QUOTES, all_quotes.getQuotes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return modelAndView;
	}
	
	
}