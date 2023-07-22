package org.llin.twelvequotes.controller;

import java.io.IOException;

import org.llin.twelvequotes.model.SingleQuoteDetailRequest;
import org.llin.twelvequotes.model.SingleQuoteDetailResponse;
import org.llin.twelvequotes.util.JsonUtilQuoteDetailResponse;
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
public class QuoteDetailResponseController extends Base {
	private static final Logger logger = LoggerFactory.getLogger(QuoteDetailResponseController.class);

	//@Value("api.polygon.apiKey")
	private String apiKey = "TcrLC_fRVS3Dr02Q3yyIyi1voM40P6ce";
	
	private String url = "https://api.polygon.io/v2/aggs/ticker/";
		
	@PostMapping("/display")
	public ModelAndView getAllQuotes(@ModelAttribute SingleQuoteDetailRequest sqdReq,
			   													  HttpSession session) {

		StringBuilder sb = new StringBuilder(url); 
		ModelAndView modelAndView = new ModelAndView("quoteDetailResponse");

		sb.append(sqdReq.getStockTicker()).append("/");
		sb.append("range/");
		sb.append(sqdReq.getMultiplier()).append("/");
		sb.append(sqdReq.getTimeSpan().getUnit()).append("/");
		sb.append(sqdReq.getFromDate()).append("/");		
		sb.append(sqdReq.getToDate()).append("?");
		sb.append("adjusted=").append(sqdReq.isAdjusted()).append("&");
		sb.append("sort=").append(sqdReq.isSort()).append("&");
		sb.append("limit=").append(sqdReq.getLimit()).append("&");
		sb.append("apiKey=").append(apiKey);
		
		
		SingleQuoteDetailResponse response = new SingleQuoteDetailResponse(); 
		
		JsonUtilQuoteDetailResponse util = new JsonUtilQuoteDetailResponse(sb.toString());
		
		try {
			response = util.retrieveObject();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		modelAndView.addObject(QUOTE_DETAIL_RESPONSE, response);
		return modelAndView;
	}
		
}  