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
import org.llin.twelvequotes.controller.StockTypeController;
import org.llin.twelvequotes.model.AllQuotes;
import org.llin.twelvequotes.model.SingleQuote;
import org.llin.twelvequotes.util.JsonUtilSingleQuote;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import jakarta.servlet.http.HttpSession;

public class StockTypeControllerTest {
	private String url = "https://api.twelvedata.com/stocks";
    private MockMvc mockMvc;
    private MockHttpSession mockSess;
    
    @Mock
    private HttpSession session;

    @InjectMocks
    private StockTypeController<SingleQuote> stockTypeController;
    
    @BeforeEach
    public void setup() throws IOException {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(stockTypeController).build();
        mockSess = new MockHttpSession();
        mockSess.setAttribute(Constants.ALL_QUOTES, getAllQuotes());
    }  
    
    @Test
    public void testShowInstruction() throws Exception {
        mockMvc.perform(get("/stockType/list"))
            .andExpect(status().isOk())
            .andExpect(view().name("stockType"));
    }    

    @Test
    public void testHandleFormSubmissionCountryRedirect() throws Exception {
    
        mockMvc.perform(post("/stockType/submit")
        		.session(mockSess)
        		.param("selectedCountry","US")
        		.param("selectedType","Common Stock")
        		.param("submit","")	
        	)
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/country/list"));
    }    
    
    @Test
    public void testHandleFormSubmissionQuotesRedirect() throws Exception {
        mockMvc.perform(post("/stockType/submit")
        		.session(mockSess)
        		.param("selectedCountry","US")
        		.param("selectedType","Common Stock")
        		.param("submit","submit")
        	)
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/quotes/list"));
    }      
 
    private AllQuotes<SingleQuote> getAllQuotes() throws IOException {
    	JsonUtilSingleQuote<SingleQuote> jsUtil = new JsonUtilSingleQuote<>(url);
    	AllQuotes<SingleQuote> allQuotes = new AllQuotes<SingleQuote>(jsUtil.retrieveObject()); 
    	allQuotes.populateForOnlySelectedCountry();
    	return allQuotes;
    }
}
