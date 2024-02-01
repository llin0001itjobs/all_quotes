package org.llin.twelvequotes.test;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.llin.twelvequotes.model.AllQuotes;
import org.llin.twelvequotes.model.SingleQuote;
import org.llin.twelvequotes.util.ChunkerUtil;
import org.llin.twelvequotes.util.JsonUtilQuoteDetailResponse;
import org.llin.twelvequotes.util.JsonUtilSingleQuote;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.HttpClientErrorException;

import com.opencsv.exceptions.CsvValidationException;

@SpringBootTest
public class UtilTests {

	private static final String URL = "https://api.twelvedata.com/stocks";
	
    @Value("${api.twelve-quotes.presentation.quotesPerPage}")
    private int quotesPerPage;

    @Value("${api.twelve-quotes.presentation.tabsPerLine}")
    private int tabsPerLine;
    
    @Test
    void testChunker() throws IOException {
		JsonUtilSingleQuote<SingleQuote> jsUtil = new JsonUtilSingleQuote<>(URL);
		List<SingleQuote> list = jsUtil.retrieveObject();
		AllQuotes<SingleQuote> aq = new AllQuotes<>(list); 
		ChunkerUtil.chunkList(aq, quotesPerPage, tabsPerLine);
	}
    
    @Test
    void testJsonUtilQuoteDetail() throws HttpClientErrorException, CsvValidationException, NumberFormatException, IOException {
    	JsonUtilQuoteDetailResponse juQdr = new JsonUtilQuoteDetailResponse(URL);
    	juQdr.retrieveObject();
    }   
    
    @Test
    void testJsonUtilSingleQuote() throws IOException {
		JsonUtilSingleQuote<SingleQuote> jsUtil = new JsonUtilSingleQuote<>(URL);
		List<SingleQuote> list = jsUtil.retrieveObject();
    }      
     
}
