package org.llin.twelvequotes.test.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.llin.twelvequotes.Constants;
import org.llin.twelvequotes.controller.CountryController;
import org.llin.twelvequotes.model.AllQuotes;
import org.llin.twelvequotes.model.SingleQuote;
import org.llin.twelvequotes.util.JsonUtilSingleQuote;
import org.llin.twelvequotes.util.QuotesRetriever;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import jakarta.servlet.http.HttpSession;

public class CountryControllerTest {
	private String url = "https://api.twelvedata.com/stocks";
    private MockMvc mockMvc;
    private MockHttpSession mockSess;
    
    @Mock
    private HttpSession session;
	
    @InjectMocks
    private CountryController<SingleQuote> countryController;
    
    @Mock
    private QuotesRetriever<SingleQuote> quotesRetriever;
    
    @BeforeEach
    public void setup() throws Exception {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(countryController).build();
        mockSess = new MockHttpSession();
        mockSess.setAttribute(Constants.ALL_QUOTES, getAllQuotes());
    }    
    
    @Test
    public void testGetAllQuotes() throws Exception {
        mockMvc.perform(get("/country/list")
        		.session(mockSess)
        		.param("selectedCountry", "US")
        	)
            .andExpect(status().isOk())
            .andExpect(view().name("country"));
    }    
    
    @Test
    public void testHandleFormSubmissionForStockTypeRedirect() throws Exception {
        mockMvc.perform(post("/country/submit")
        		.session(mockSess)
        		.param("selectedCountry", "US")
        		.param("submit", "stockType")
        )
        .andExpect(status().is3xxRedirection())
        .andExpect(redirectedUrl("/stockType/list"));

    }
        
    @Test    
    public void testHandleFormSubmissionForListQuotesRedirect() throws Exception {
        mockMvc.perform(post("/country/submit")
        		.session(mockSess)
        		.param("selectedCountry", "US")
        		.param("submit", "listQuotes")
        	)
        .andExpect(status().is3xxRedirection())
        .andExpect(redirectedUrl("/quotes/list"));
    }    
    
    private AllQuotes<SingleQuote> getAllQuotes() throws IOException {
    	JsonUtilSingleQuote<SingleQuote> jsUtil = new JsonUtilSingleQuote<>(url);
    	AllQuotes<SingleQuote> allQuotes = new AllQuotes<SingleQuote>(jsUtil.retrieveObject()); 
    	return allQuotes;
    }
}
