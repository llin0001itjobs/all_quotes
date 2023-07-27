package org.llin.twelvequotes.controller;

import java.util.ArrayList;
import java.util.List;

import org.llin.twelvequotes.controller.model.SearchRequest;
import org.llin.twelvequotes.controller.util.ChunkerUtil;
import org.llin.twelvequotes.controller.util.TabsUtil;
import org.llin.twelvequotes.model.AllQuotes;
import org.llin.twelvequotes.model.SingleQuote;
import org.llin.twelvequotes.model.SingleQuoteDetailRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/quotes")
public class QuoteDetailRequestController<T extends SingleQuote> extends Base {
	private static final Logger logger = LoggerFactory.getLogger(QuoteDetailRequestController.class);
	
	private int LIST_INCREMENT = 20;
	
	@SuppressWarnings("unchecked")
	@GetMapping("/list")
	public ModelAndView getAllQuotes(HttpSession session) {
		
		ModelAndView modelAndView = new ModelAndView("quotes");
		
		AllQuotes<T> all_quotes = (AllQuotes<T>)session.getAttribute(ALL_QUOTES);
		
		ChunkerUtil.chunkList(all_quotes, LIST_INCREMENT);
		TabsUtil.generateTabs(all_quotes);
		
		modelAndView.addObject(ALL_QUOTES, all_quotes);
		modelAndView.addObject(SEARCH_REQUEST, new SearchRequest());
		
		return modelAndView;
	}

	@SuppressWarnings("unchecked")
	@GetMapping("/symbol/{symbol}")
	public ModelAndView getSingleQuoteBySymbol(@PathVariable String symbol, 
			                                            HttpSession session) {
		
		SingleQuoteDetailRequest sqdR = new SingleQuoteDetailRequest();
		sqdR.setStockTicker(symbol);
		
		ModelAndView modelAndView = new ModelAndView("quoteDetailRequest");

		AllQuotes<T> all_quotes = (AllQuotes<T>)session.getAttribute(ALL_QUOTES);
		all_quotes.setSelectedSymbol(symbol);
		
		modelAndView.addObject(ALL_QUOTES, all_quotes);
		modelAndView.addObject(QUOTE_DETAIL_REQUEST, sqdR);
		return modelAndView;
	}
	
	@SuppressWarnings("unchecked")
	@PostMapping("/filter")
	public ModelAndView filterQuotes(@ModelAttribute(SEARCH_REQUEST) SearchRequest sReq,
									                                   HttpSession session) {
				
		ModelAndView modelAndView = new ModelAndView("quotes");

		AllQuotes<T> all_quotes = (AllQuotes<T>)session.getAttribute(ALL_QUOTES);

		List<List<SingleQuote>> chunkedList = all_quotes.getChunkedList();
		List<SingleQuote> list = new ArrayList<>();
		System.out.println(list.size());
		System.out.println("sReq [" + sReq.getName() + "] [" + sReq.getSymbol() + "]");
		
		for (List<SingleQuote> l : chunkedList) {
			for (SingleQuote s : l) {
				if (!sReq.getName().isBlank()) {
					if (s.getName().toUpperCase().contains(sReq.getName().trim().toUpperCase())) {
						list.add(s);
					}
				}
				if (!sReq.getSymbol().isBlank()) {
					if (s.getSymbol().toUpperCase().startsWith(sReq.getSymbol().trim().toUpperCase())) {
						list.add(s);
					}	
				}
			}	
		}
		
		System.out.println(list.size());
		
		if (!list.isEmpty()) {
			all_quotes.setList(list);
			ChunkerUtil.chunkList(all_quotes, LIST_INCREMENT);
			TabsUtil.generateTabs(all_quotes);
		}
		modelAndView.addObject(ALL_QUOTES, all_quotes);
		
		return modelAndView;
	}
		
}
