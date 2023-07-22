package org.llin.twelvequotes.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.llin.twelvequotes.model.SingleQuoteDetailResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class JsonUtilQuoteDetailResponse {
	    
    private String urlApi;
    private boolean failed;
	
	public JsonUtilQuoteDetailResponse() {}
		
	public JsonUtilQuoteDetailResponse(String urlApi) {
		this.urlApi = urlApi;
	}
		
	public boolean isFailed() {
		return failed;
	}

	public SingleQuoteDetailResponse retrieveObject() throws IOException {
		SingleQuoteDetailResponse sqdResponse = new SingleQuoteDetailResponse();
		RestTemplate restTemplate = new RestTemplate();
		
		System.out.println(urlApi);
		ResponseEntity<String> response = restTemplate.getForEntity(urlApi, String.class);		
		if (!response.getStatusCode().is2xxSuccessful()) {
			failed = true;
			return sqdResponse;
		}
		failed = false;
		
		return mapJsonText(response.getBody());
	}

	SingleQuoteDetailResponse mapJsonText(String jsonText) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		SingleQuoteDetailResponse sqdResponse = objectMapper.readValue(jsonText, SingleQuoteDetailResponse.class);


		return sqdResponse; 
	}

	public static void main(String[] args) {
		String _URL = "https://api.polygon.io/v2/aggs/ticker/AAPL/range/1/day/2023-01-09/2023-01-09?adjusted=true&sort=asc&limit=120&apiKey=TcrLC_fRVS3Dr02Q3yyIyi1voM40P6ce";
		
		try {
			JsonUtilQuoteDetailResponse jsUtil = new JsonUtilQuoteDetailResponse(_URL);
			SingleQuoteDetailResponse sqlResponse = jsUtil.retrieveObject();
			
			System.out.println(sqlResponse);
			
		} catch (IOException eIO) {
			System.out.println(eIO);
		}
		
	}
}


