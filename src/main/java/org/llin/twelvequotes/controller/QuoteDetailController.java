package org.llin.twelvequotes.controller;

import org.llin.twelvequotes.model.SingleQuoteDetailRequest;
import org.llin.twelvequotes.model.SingleQuoteDetailResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/quoteDetail")
public class QuoteDetailController extends Base {
	private static final Logger logger = LoggerFactory.getLogger(QuoteDetailController.class);

	@Value("")
	private String _hrefApi;
		
	@PostMapping("/list")
	public ModelAndView getAllQuotes(@ModelAttribute SingleQuoteDetailRequest sqdReq,
			   													  HttpSession session) {

		ModelAndView modelAndView = new ModelAndView("quoteDetail");

		SingleQuoteDetailResponse response = new SingleQuoteDetailResponse(); 
		
		modelAndView.addObject(QUOTE_DETAIL, response);
		
		return modelAndView;
	}
		
}  