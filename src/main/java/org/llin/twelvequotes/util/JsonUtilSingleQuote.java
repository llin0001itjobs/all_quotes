package org.llin.twelvequotes.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.llin.twelvequotes.model.SingleQuote;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class JsonUtilSingleQuote<T extends SingleQuote> {
	    
    private String urlApi;
    
    private static final String ARRAY_PARENT = "data";
	
	public JsonUtilSingleQuote() {
		
	}
		
	public JsonUtilSingleQuote(String urlApi) {
		this.urlApi = urlApi;
	}
			
	public List<T> retrieveObject() throws IOException {
		List<T> list = new ArrayList<>();
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.getForEntity(urlApi, String.class);		
		if (!response.getStatusCode().is2xxSuccessful()) {
			return list;				
		}
		return mapJsonText(response.getBody());
	}

	 List<T> mapJsonText(String jsonText) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();

		JsonNode jsonNode = objectMapper.readTree(jsonText.getBytes());
		jsonNode = jsonNode.findValue(ARRAY_PARENT);
		TypeReference<List<T>> typeReference = new TypeReference<List<T>>() {};
		List<T> resultList = (List<T>) objectMapper.readValue(jsonNode.traverse(), typeReference);

		return resultList; 
	}

	public static void main(String[] args) {
		try {
			JsonUtilSingleQuote<SingleQuote> jsUtil = new JsonUtilSingleQuote<>("https://api.twelvedata.com/stocks");
			List<SingleQuote> list = jsUtil.retrieveObject();
			for (int i=0; i < 100; i++) {
				System.out.println(list.get(i));
			}
		} catch (IOException eIO) {
			System.out.println(eIO);
		}
	}
}


