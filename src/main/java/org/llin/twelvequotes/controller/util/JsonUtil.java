package org.llin.twelvequotes.controller.util;

import java.io.IOException;
import java.util.List;

import org.llin.twelvequotes.model.SingleQuote;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class JsonUtil {
	
	//@Value("${api.twelve-quotes}")
    //private String _HREF_API;
    
    private String _HREF_API="https://api.twelvedata.com/stocks";
    
    
	private static final String ARRAY_PARENT = "data";
	
	public List<SingleQuote> retrieveObject() throws IOException {
		return mapJsonText(getJsonText());
	}

	private String getJsonText() throws IOException {
		String jsonText = null;

		RestTemplate restTemplate = new RestTemplate();
		jsonText = restTemplate.getForObject(_HREF_API, String.class);

		return jsonText;
	}

	private List<SingleQuote> mapJsonText(String jsonText) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();

		JsonNode jsonNode = objectMapper.readTree(jsonText.getBytes());
		jsonNode = jsonNode.findValue(ARRAY_PARENT);
		TypeReference<List<SingleQuote>> typeReference = new TypeReference<List<SingleQuote>>() {};
		List<SingleQuote> resultList = (List<SingleQuote>) objectMapper.readValue(jsonNode.traverse(), typeReference);

		return resultList; 
	}

	public static void main(String[] args) {
		try {
			JsonUtil jsUtil = new JsonUtil();
			List<SingleQuote> list = jsUtil.retrieveObject();
		} catch (IOException eIO) {
			System.out.println(eIO);
		}
	}
}


