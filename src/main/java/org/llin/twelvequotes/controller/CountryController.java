package org.llin.twelvequotes.controller;

import java.io.IOException;
import java.util.List;

import org.llin.twelvequotes.Constants;
import org.llin.twelvequotes.model.AllQuotes;
import org.llin.twelvequotes.model.SingleQuote;
import org.llin.twelvequotes.service.SingleQuoteService;
import org.llin.twelvequotes.util.JsonUtilSingleQuote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/country")
public class CountryController<T extends SingleQuote> extends Constants {
	
	@Autowired
	private SingleQuoteService<T> sqs;
	
	@Value("${api.twelve-quotes.url}")
	private String url;
	
	@SuppressWarnings("unchecked")
	@GetMapping("/list")
	public ModelAndView getAllQuotes(HttpSession session) {
		ModelAndView modelAndView = new ModelAndView("country");

		try {			
			if (session.getAttribute(ALL_QUOTES) == null) {
				List<SingleQuote> list = sqs.findAll();
				AllQuotes<SingleQuote> all_quotes = new AllQuotes<SingleQuote>(list);  
				if (sqs.count() == 0) {
					JsonUtilSingleQuote<SingleQuote> jsonUtil = new JsonUtilSingleQuote<>(url);
					all_quotes = new AllQuotes<>(jsonUtil.retrieveObject());
					sqs.saveAll(all_quotes.getAll());
				}
				all_quotes.populateCountrySet();
				session.setAttribute(ALL_QUOTES, all_quotes);
			}
			modelAndView.addObject(ALL_QUOTES, (AllQuotes<T>) session.getAttribute(ALL_QUOTES));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return modelAndView;
	}

	@SuppressWarnings("unchecked")
	@PostMapping("/submit")
	public String handleFormSubmission(@RequestParam("selectedCountry") String selectedCountry,
												@RequestParam("submit") String submit,
												                   HttpSession session) {
		String redirect = "redirect:/";

		if (submit.equals("stockType")) {
			redirect = "redirect:/stockType/list";
		}
		
		if (submit.equals("listQuotes")) {
			redirect = "redirect:/quotes/list";
		}
		
		AllQuotes<T> all_quotes = (AllQuotes<T>) session.getAttribute(ALL_QUOTES);

		all_quotes.setSelectedCountry(selectedCountry);
		all_quotes.setSelectedType("");
		all_quotes.populateForOnlySelectedCountry();
		all_quotes.populateTypeSet();
		all_quotes.populateForOnlySelectedType();
		
		session.setAttribute(ALL_QUOTES, all_quotes);

		return redirect; // Redirect to a success page
	}
	
	

}