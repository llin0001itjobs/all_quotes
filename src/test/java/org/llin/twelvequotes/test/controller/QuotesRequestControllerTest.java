package org.llin.twelvequotes.test.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.llin.twelvequotes.Constants;
import org.llin.twelvequotes.config.TwelveQuotesConfig;
import org.llin.twelvequotes.controller.QuotesController;
import org.llin.twelvequotes.model.AllQuotes;
import org.llin.twelvequotes.model.QuoteDetailRequest;
import org.llin.twelvequotes.model.SingleQuote;
import org.llin.twelvequotes.util.JsonUtilSingleQuote;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpSession;

@SpringBootTest(classes = {TwelveQuotesConfig.class})
public class QuotesRequestControllerTest {
	private String url = "https://api.twelvedata.com/stocks";
    private MockMvc mockMvc;
    private MockHttpSession mockSess;

    @Mock
    private HttpSession session;

    @InjectMocks
    private QuotesController<SingleQuote> quoteRequestController;
    
    @BeforeEach
    public void setup() throws Exception {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(quoteRequestController).build();
        mockSess = new MockHttpSession();
        mockSess.setAttribute(Constants.ALL_QUOTES, getAllQuotes());
    }   
    
    @Test
    public void testShowList() throws Exception {
        mockMvc.perform(get("/quotes/list")
        	   .session(mockSess))
               .andExpect(status().isOk())
               .andExpect(view().name("quotes"));     
    }    
    
    @Test
    public void testGetSingleQuoteBySymbol() {
        String symbol = "AAPL"; 

        AllQuotes<SingleQuote> allQuotes = new AllQuotes<>(new ArrayList<SingleQuote>()); 
        when(session.getAttribute(Constants.ALL_QUOTES)).thenReturn(allQuotes);

        ModelAndView modelAndView = quoteRequestController.getSingleQuoteBySymbol(symbol, session);

        assertEquals("quoteRequest", modelAndView.getViewName()); // Verify the expected view name

        // Verify that the AllQuotes object was updated with the selected symbol
        assertEquals(symbol, allQuotes.getSelectedSymbol());

        // Verify that QuoteDetailRequest is added to the ModelAndView
        QuoteDetailRequest sqdR = (QuoteDetailRequest) modelAndView.getModel().get(Constants.QUOTE_REQUEST);
        assertEquals(symbol, sqdR.getStockTicker());
        // Add further assertions for QuoteDetailRequest if needed
    }    
    
    private AllQuotes<SingleQuote> getAllQuotes() throws IOException {
    	JsonUtilSingleQuote<SingleQuote> jsUtil = new JsonUtilSingleQuote<>(url);
    	AllQuotes<SingleQuote> allQuotes = new AllQuotes<SingleQuote>(jsUtil.retrieveObject()); 
    	return allQuotes;
    }    
}
