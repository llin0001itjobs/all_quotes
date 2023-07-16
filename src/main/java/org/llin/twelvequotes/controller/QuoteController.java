package org.llin.twelvequotes.controller;

import org.llin.twelvequotes.model.AllQuotes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/quotes")
public class QuoteController {
	private static final Logger logger = LoggerFactory.getLogger(QuoteController.class);

	@Value("")
	private String _hrefApi;
	
	@GetMapping("/list")
	public ModelAndView getAllQuotes( HttpSession session) {

		ModelAndView modelAndView = new ModelAndView("quotes");
		AllQuotes all_quotes = (AllQuotes)session.getAttribute(CountryController.ALL_QUOTES);
		
		modelAndView.addObject(CountryController.ALL_QUOTES, all_quotes);
		
		return modelAndView;
	}

	@GetMapping("/symbol/@{symbol}")
	public ModelAndView getSingleQuoteBySymbol(@PathVariable String symbol, 
			                                    HttpSession session) {
		
		ModelAndView modelAndView = new ModelAndView("symbol");

		
		
		return modelAndView;
	}
}