package org.llin.twelvequotes.util;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public class URLParams {
	private static final String _URL= "https://api.polygon.io/v2/aggs/ticker/AAPL/range/1/day/2023-01-09/2023-01-09?adjusted=true&sort=asc&limit=149998&apiKey=TcrLC_fRVS3Dr02Q3yyIyi1voM40P6ce";
    private String ticker;
    private String range;
    private String frequency;
    private String fromDate;
    private String toDate;
    private boolean adjusted;
    private String sort;
    private int limit;
    private String apiKey;

    public URLParams(String url) {
        UriComponents uriComponents = UriComponentsBuilder.fromUriString(url).build();
        // https://api.polygon.io/v2/aggs/ticker/AAPL/range/1/day/2023-01-09/2023-01-09?adjusted=true&sort=asc&limit=149998&apiKey=TcrLC_fRVS3Dr02Q3yyIyi1voM40P6ce
        // Extract path variables
        this.ticker = uriComponents.getPathSegments().get(3); // Assumes 'ticker' is at index 4
        this.range = uriComponents.getPathSegments().get(5); // Assumes 'range' is at index 6
        this.frequency = uriComponents.getPathSegments().get(6); // Assumes 'frequency' is at index 7
        this.fromDate = uriComponents.getPathSegments().get(7); // Assumes 'from' is at index 7
        this.toDate = uriComponents.getPathSegments().get(8); // Assumes 'to' is at index 7
        
        // Extract query parameters
        this.adjusted = Boolean.parseBoolean(uriComponents.getQueryParams().getFirst("adjusted")); // Assumes 'adjusted' query parameter exists
        this.sort = uriComponents.getQueryParams().getFirst("sort"); // Assumes 'sort' query parameter exists
        this.limit = Integer.parseInt(uriComponents.getQueryParams().getFirst("limit")); // Assumes 'limit' query parameter exists
        this.apiKey = uriComponents.getQueryParams().getFirst("apiKey"); // Assumes 'apiKey' query parameter exists
    }

    public String getTicker() {
		return ticker;
	}

	public void setTicker(String ticker) {
		this.ticker = ticker;
	}

	public String getRange() {
		return range;
	}

	public void setRange(String range) {
		this.range = range;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public boolean isAdjusted() {
		return adjusted;
	}

	public void setAdjusted(boolean adjusted) {
		this.adjusted = adjusted;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	// Example usage
    public static void main(String[] args) {        
        URLParams params = new URLParams(_URL);

        System.out.println("Ticker: " + params.getTicker());
        System.out.println("Range: " + params.getRange());
        System.out.println("Frequency: " + params.getFrequency());
        System.out.println("From Date: " + params.getFromDate());
        System.out.println("To Date: " + params.getToDate());
        System.out.println("Adjusted: " + params.isAdjusted());
        System.out.println("Sort: " + params.getSort());
        System.out.println("Limit: " + params.getLimit());
        System.out.println("API Key: " + params.getApiKey());
    }
}
