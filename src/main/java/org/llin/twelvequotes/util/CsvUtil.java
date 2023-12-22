package org.llin.twelvequotes.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.llin.twelvequotes.model.QuoteDetailResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
 
public class CsvUtil {

	public static List<QuoteDetailResponse> convertCsvToList(InputStream is, String s) throws IOException, CsvValidationException, NumberFormatException {
		
		List<QuoteDetailResponse> stockDataList = new ArrayList<>();
				
		try (CSVReader cvsReader = new CSVReaderBuilder(new BufferedReader(consumeInput(is,s))).withSkipLines(1).build()) {
			String[] line;
			while ((line = cvsReader.readNext()) != null) {
				QuoteDetailResponse stockData = new QuoteDetailResponse();
				stockData.setDate(line[0]);
				stockData.setOpen(Double.parseDouble(line[1]));
				stockData.setHigh(Double.parseDouble(line[2]));
				stockData.setLow(Double.parseDouble(line[3]));
				stockData.setClose(Double.parseDouble(line[4]));
				stockData.setAdjustedClose(Double.parseDouble(line[5]));
				stockData.setVolume(Long.parseLong(line[6]));
				stockDataList.add(stockData);
			}
		}
		return stockDataList;
	}
	
	public static String convertCsvToJson(InputStream is, String s) throws IOException, CsvValidationException, NumberFormatException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(convertCsvToList(is, s));
	}
	
	public static String convertCsvToJson(List<QuoteDetailResponse> list) throws IOException, CsvValidationException, NumberFormatException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(list);
	}
	
	private static Reader consumeInput(InputStream is, String s) {	
		return is != null ? new InputStreamReader(is) : (s != null ? new StringReader(s) :  new StringReader(""));		
	}
	
}
