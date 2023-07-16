package org.llin.twelvequotes.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class APICallValidator {
	//https://api.polygon.io/v2/aggs/ticker/AAPL/range/1/day/2023-01-09/2023-01-09?adjusted=true&sort=asc&limit=120&apiKey=TcrLC_fRVS3Dr02Q3yyIyi1voM40P6ce
	
     private static final String API_CALL_REGEX = "api\\.polygon\\.io/v2/aggs/ticker/\\w+/range/\\d+/(minute|hour|day|week|month|quarter|year)/\\d{4}-\\d{2}-\\d{2}/\\d{4}-\\d{2}-\\d{2}\\?((adjusted=(true|false))&)?((sort=(asc|desc))&)?((limit=\\d[5])&)?apiKey=\\w+";


    public static boolean isValidMainAPICall(String apiCall) {
        Pattern pattern = Pattern.compile(API_CALL_REGEX);
        Matcher matcher = pattern.matcher(apiCall);
        return matcher.matches();
    }
    
    public static void main(String[] args) {
        String apiCall1 = "api.polygon.io/v2/aggs/ticker/AAPL/range/1/day/2023-01-09/2023-01-09?adjusted=true&sort=asc&limit=120&apiKey=TcrLC_fRVS3Dr02Q3yyIyi1voM40P6ce";
        String apiCall2 = "api.polygon.io/v2/aggs/ticker/AAPL/range/1/day/2023-01-09/2023-01-09?apiKey=TcrLC_fRVS3Dr02Q3yyIyi1voM40P6ce";
        String apiCall3 = "api.polygon.io/v2/aggs/ticker/AAPL/range/1/day/2023-01-09/2023-01-09?adjusted=true&sort=asc&apiKey=TcrLC_fRVS3Dr02Q3yyIyi1voM40P6ce";
        String apiCall4 = "api.polygon.io/v2/aggs/ticker/AAPL/range/1/day/2023-01-09/2023-01-09?adjusted=true&sort=asc&limit=120&apiKey=TcrLC_fRVS3Dr02Q3yyIyi1voM40P6ce";
        String apiCall5 = "/api/v1/resource/123/456"; // Invalid API call

        System.out.println(APICallValidator.isValidMainAPICall(apiCall1) + " for " + apiCall1); // true
        System.out.println(APICallValidator.isValidMainAPICall(apiCall2) + " for " + apiCall2); // true
        System.out.println(APICallValidator.isValidMainAPICall(apiCall3) + " for " + apiCall3); // true
        System.out.println(APICallValidator.isValidMainAPICall(apiCall4) + " for " + apiCall4); // true
        System.out.println(APICallValidator.isValidMainAPICall(apiCall5) + " for " + apiCall5); // false
    }
}
