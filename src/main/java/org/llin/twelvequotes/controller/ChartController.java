package org.llin.twelvequotes.controller;

import org.llin.twelvequotes.Constants;
import org.llin.twelvequotes.model.QuoteResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/chart")
public class ChartController extends Constants {

    @GetMapping("/show")
    public ModelAndView chart(HttpSession session) {
		ModelAndView modelAndView = new ModelAndView("chart");
		QuoteResponse response = (QuoteResponse)session.getAttribute(QUOTE_RESPONSE);
		if (response == null) {	
		}
		modelAndView.addObject(QUOTE_RESPONSE, response);
        return modelAndView ;
    }
}
