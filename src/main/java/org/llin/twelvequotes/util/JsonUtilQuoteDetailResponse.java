package org.llin.twelvequotes.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.llin.twelvequotes.model.QuoteDetailResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.opencsv.exceptions.CsvValidationException;

@Component
public class JsonUtilQuoteDetailResponse {
	
	private ResponseEntity<String> responseEntity = null;
    private String urlApi;
    
    private List<QuoteDetailResponse> list = new ArrayList<>();
	
	public JsonUtilQuoteDetailResponse() {}
		
	public JsonUtilQuoteDetailResponse(String urlApi) {
		this.urlApi = urlApi;
	}
			
	public List<QuoteDetailResponse> retrieveObject() throws IOException, CsvValidationException, NumberFormatException, HttpClientErrorException {
		if (list.size() > 0) {
			return list;
		}
		RestTemplate restTemplate = new RestTemplate();

		responseEntity = restTemplate.getForEntity(urlApi, String.class);		
		HttpStatusCode sc = responseEntity.getStatusCode();
		
		if (!sc.is2xxSuccessful()) {
			return list;
		}
		list = mapJsonText(responseEntity.getBody());
		return list;		
	}

	public String retrieveJson() throws IOException, CsvValidationException, NumberFormatException {			
		return CsvUtil.convertCsvToJson(retrieveObject());		
	}
	
	List<QuoteDetailResponse> mapJsonText(String jsonText) throws IOException, CsvValidationException, NumberFormatException {	
		return CsvUtil.convertCsvToList(null,jsonText); 
	}

	public void clearList() {
		list.clear();
	}
	
	public String getUrlApi() {
		return urlApi;
	}

	public void setUrlApi(String urlApi) {
		this.urlApi = urlApi;
	}

	public List<QuoteDetailResponse> getList() {
		return list;
	}

	public void setList(List<QuoteDetailResponse> list) {
		this.list = list;
	}
	
	public ResponseEntity<String> getResponseEntity() {
		return responseEntity;
	}

	public void setResponseEntity(ResponseEntity<String> responseEntity) {
		this.responseEntity = responseEntity;
	}
	
	public static void main(String[] args) {
		String _URL = "https://query1.finance.yahoo.com/v7/finance/download/IBM?period1=0&period2=9999999999&interval=1d&events=history";
		
		try {
			JsonUtilQuoteDetailResponse jsUtil = new JsonUtilQuoteDetailResponse(_URL);
						
			System.out.println(jsUtil.retrieveObject());
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (CsvValidationException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}		
	}



					
}


