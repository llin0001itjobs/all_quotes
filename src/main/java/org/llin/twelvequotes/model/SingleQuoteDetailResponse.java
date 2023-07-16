package org.llin.twelvequotes.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Based on polygon.io 
 */
public class SingleQuoteDetailResponse {

	private String ticker;		
	private int queryCount;
	private int resultsCount;
	private boolean adjusted;
	private List<DetailResponseResults> results = new ArrayList<>();	
	private String status;
	private String request_id;
	private int count;
		
	public String getTicker() {
		return ticker;
	}

	public void setTicker(String ticker) {
		this.ticker = ticker;
	}

	public int getQueryCount() {
		return queryCount;
	}

	public void setQueryCount(int queryCount) {
		this.queryCount = queryCount;
	}

	public int getResultsCount() {
		return resultsCount;
	}

	public void setResultsCount(int resultsCount) {
		this.resultsCount = resultsCount;
	}

	public boolean isAdjusted() {
		return adjusted;
	}

	public void setAdjusted(boolean adjusted) {
		this.adjusted = adjusted;
	}

	public List<DetailResponseResults> getResults() {
		return results;
	}

	public void setResults(List<DetailResponseResults> results) {
		this.results = results;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRequest_id() {
		return request_id;
	}

	public void setRequest_id(String request_id) {
		this.request_id = request_id;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "SingleQuoteResponse [ticker=" + ticker + ", queryCount=" + queryCount + ", resultsCount=" + resultsCount
				+ ", adjusted=" + adjusted + ", results=" + results + ", status=" + status + ", request_id="
				+ request_id + ", count=" + count + "]";
	}
	
}
