package org.llin.twelvequotes.controller;

import java.io.IOException;
import java.util.Map;

import org.llin.twelvequotes.Constants;
import org.llin.twelvequotes.controller.util.QuoteDetailRequestValidator;
import org.llin.twelvequotes.model.QuoteDetailRequest;
import org.llin.twelvequotes.model.QuoteResponse;
import org.llin.twelvequotes.util.JsonUtilQuoteDetailResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.opencsv.exceptions.CsvValidationException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/quoteDetail")
public class QuoteResponseController extends Constants {

	private static final String ERR_MSG_PREFIX = "Not Found:";
	@Autowired
	private ApplicationContext applicationContext;
	
	@Autowired
	private QuoteDetailRequestValidator validator;

	@Autowired
	private ErrorAttributes errorAttributes;
	
	@Value("${api.yahoo.url}")
	private String url;

	@PostMapping("/display")
	public ModelAndView getAllQuotes(@Valid @ModelAttribute QuoteDetailRequest qdReq, BindingResult result,
			HttpServletRequest request) {

		HttpSession session = request.getSession();
		validator.validate(qdReq, result);
		if (result.hasErrors()) {
			// Handle validation errors here
		}

		StringBuilder sb = new StringBuilder(url);
		ModelAndView modelAndView = new ModelAndView("quoteResponse");

		sb.append(qdReq.getStockTicker()).append("?");
		sb.append("period1=");
		sb.append(qdReq.getPeriodTimeStamp1()).append("&");
		sb.append("period2=");
		sb.append(qdReq.getPeriodTimeStamp2()).append("&");
		sb.append("interval=");
		sb.append(qdReq.getInterval()).append("&");
		sb.append("events=");
		sb.append(qdReq.getEvents());

		QuoteResponse response = new QuoteResponse();
		response.setTicker(qdReq.getStockTicker());

		JsonUtilQuoteDetailResponse util = applicationContext.getBean(JsonUtilQuoteDetailResponse.class);
		util.setUrlApi(sb.toString());

		try {
			response.setList(util.retrieveObject());
			response.setJsonText(util.retrieveJson());
		} catch (HttpClientErrorException e) {
			response.setErrorMsg(cleanUpMessage(e.getMessage()));			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (CsvValidationException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		session.setAttribute(QUOTE_RESPONSE, response);
		modelAndView.addObject(QUOTE_RESPONSE, response);
		return modelAndView;
	}

	private String cleanUpMessage(String msg) {
		msg = msg.replace("\"", "");
		return msg.substring(msg.lastIndexOf(ERR_MSG_PREFIX) + ERR_MSG_PREFIX.length());		
	}
}