package org.llin.twelvequotes.controller;

import java.io.IOException;
import java.util.List;

import org.llin.twelvequotes.controller.util.JsonUtil;
import org.llin.twelvequotes.model.AllQuotes;
import org.llin.twelvequotes.model.SingleQuote;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/quotes")
public class QuotesController {
	
	private static final String QUOTES = "QUOTES";
	private static final String SELECT_COUNTRY = "SELECT_COUNTRY";
	private static final String SELECT_CURRENCY = "SELECT_CURRENCY";
	private static final String SELECT_EXCHANGE = "SELECT_EXCHANGE";
	private static final String SELECT_MIC_CODE = "SELECT_MIC_CODE";
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
			modelAndView.addObject(SELECT_CURRENCY, all_quotes.getCurrencySet());
			modelAndView.addObject(SELECT_EXCHANGE, all_quotes.getExchangeSet());
			modelAndView.addObject(SELECT_MIC_CODE, all_quotes.getMic_codeSet());
			modelAndView.addObject(SELECT_TYPE, all_quotes.getTypeSet());
			modelAndView.addObject(QUOTES, all_quotes.getQuotes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return modelAndView;
	}
	
	
}