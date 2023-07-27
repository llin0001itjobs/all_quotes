package org.llin.twelvequotes.controller;

import java.util.ArrayList;
import java.util.List;

import org.llin.twelvequotes.controller.util.ChunkerUtil;
import org.llin.twelvequotes.model.AllQuotes;
import org.llin.twelvequotes.model.SingleQuote;
import org.llin.twelvequotes.model.SingleQuoteDetailRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/quotes")
public class QuoteDetailRequestController extends Base {
	private static final Logger logger = LoggerFactory.getLogger(QuoteDetailRequestController.class);
	
	private int LIST_INCREMENT = 20;
	
	@GetMapping("/list")
	public ModelAndView getAllQuotes(HttpSession session) {

		ModelAndView modelAndView = new ModelAndView("quotes");
		AllQuotes all_quotes = (AllQuotes)session.getAttribute(ALL_QUOTES);
		
		all_quotes.setChunkedList(ChunkerUtil.chunkList(all_quotes.getList(), LIST_INCREMENT));
		modelAndView.addObject(ALL_QUOTES, all_quotes);
		
		return modelAndView;
	}

	@GetMapping("/symbol/{symbol}")
	public ModelAndView getSingleQuoteBySymbol(@PathVariable String symbol, 
			                                            HttpSession session) {
		
		SingleQuoteDetailRequest sqdR = new SingleQuoteDetailRequest();
		sqdR.setStockTicker(symbol);
		
		ModelAndView modelAndView = new ModelAndView("quoteDetailRequest");

		AllQuotes all_quotes = (AllQuotes)session.getAttribute(ALL_QUOTES);
		all_quotes.setSelectedSymbol(symbol);
		
		modelAndView.addObject(ALL_QUOTES, all_quotes);
		modelAndView.addObject(QUOTE_DETAIL_REQUEST, sqdR);
		return modelAndView;
	}
	
	@PostMapping("/filter")
	public ModelAndView filterQuotes(@RequestAttribute String name,
									 @RequestAttribute String symbol,
									 		      HttpSession session) {
				
		ModelAndView modelAndView = new ModelAndView("quotes");

		AllQuotes all_quotes = (AllQuotes)session.getAttribute(ALL_QUOTES);

		List<List<SingleQuote>> chunkedList = all_quotes.getChunkedList();
		List<SingleQuote> list = new ArrayList<>();
		
		for (List<SingleQuote> l : chunkedList) {
			for (SingleQuote s : l) {
				if (s.getName().toUpperCase().startsWith(name.toUpperCase()) ||
					s.getSymbol().toUpperCase().startsWith(symbol.toUpperCase())) {
					list.add(s);
				}
			}	
		}
		
		if (!list.isEmpty()) {
			all_quotes.setChunkedList(ChunkerUtil.chunkList(list, LIST_INCREMENT));
		}
		modelAndView.addObject(ALL_QUOTES, all_quotes);
		
		return modelAndView;
	}
		
}