package org.llin.twelvequotes.model;

/**
* Based on polygon.io 
*/
public class SingleQuoteDetailRequest {
	
	private String stocksTicker;
	private int multiplier; 
	private TimeSpan timespan; 
	private String fromDate; 
	private String toDate;
	private boolean adjusted;
	private boolean sort;	//asc is true, desc is false
	private int limit;		//default is 5000, max is 50000
	
	
	public String getStocksTicker() {
		return stocksTicker;
	}
	public void setStocksTicker(String stocksTicker) {
		this.stocksTicker = stocksTicker;
	}
	public int getMultiplier() {
		return multiplier;
	}
	public void setMultiplier(int multiplier) {
		this.multiplier = multiplier;
	}
	public TimeSpan getTimespan() {
		return timespan;
	}
	public void setTimespan(TimeSpan timespan) {
		this.timespan = timespan;
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
	public boolean isSort() {
		return sort;
	}
	public void setSort(boolean sort) {
		this.sort = sort;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	
	@Override
	public String toString() {
		return "SingleQuoteRequest [stocksTicker=" + stocksTicker + ", multiplier=" + multiplier + ", timespan="
				+ timespan + ", fromDate=" + fromDate + ", toDate=" + toDate + ", adjusted=" + adjusted + ", sort="
				+ sort + ", limit=" + limit + "]";
	}
	
	
}
