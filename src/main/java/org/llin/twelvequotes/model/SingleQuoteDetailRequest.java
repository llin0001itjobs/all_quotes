package org.llin.twelvequotes.model;

import java.text.SimpleDateFormat;
import java.time.LocalDate;

/**
 * Based on polygon.io
 */
public class SingleQuoteDetailRequest {

	private String stockTicker;
	private int multiplier = 1;
	private TimeSpan timeSpan = TimeSpan.DAY;
	private String fromDate;
	private String toDate;
	private boolean adjusted = false;
	private boolean sort = true; // asc is true, desc is false
	private int limit = 5000; // default is 5000, max is 50000

	public SingleQuoteDetailRequest() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String currentDateStr = sdf.format(java.sql.Date.valueOf(LocalDate.now()));

		fromDate = currentDateStr;
		toDate = currentDateStr;
		
	}

	public String getStockTicker() {
		return stockTicker;
	}

	public void setStockTicker(String stockTicker) {
		this.stockTicker = stockTicker;
	}

	public int getMultiplier() {
		return multiplier;
	}

	public void setMultiplier(int multiplier) {
		this.multiplier = multiplier;
	}

	public TimeSpan getTimeSpan() {
		return timeSpan;
	}

	public void setTimeSpan(TimeSpan timeSpan) {
		this.timeSpan = timeSpan;
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
		return "SingleQuoteRequest [stocksTicker=" + stockTicker + ", multiplier=" + multiplier + ", timespan="
				+ timeSpan + ", fromDate=" + fromDate + ", toDate=" + toDate + ", adjusted=" + adjusted + ", sort="
				+ sort + ", limit=" + limit + "]";
	}

}
