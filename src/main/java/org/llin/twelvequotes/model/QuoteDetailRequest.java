package org.llin.twelvequotes.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.llin.twelvequotes.Constants;

import jakarta.validation.constraints.NotBlank;

public class QuoteDetailRequest {

	SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATEFORMAT_YYYY_MM_DD);
	
	// https://query1.finance.yahoo.com/v7/finance/download/{stock_ticker}?period1=0&period2=9999999999&interval=1d&events=history
	private String stockTicker;

	@NotBlank(message = "From Date is mandatory")
	private String period1;
	private long periodTimeStamp1;

	@NotBlank(message = "To Date is mandatory")
	private String period2;
	private long periodTimeStamp2;

	private String interval;
	private String events;

	public QuoteDetailRequest() {
		super();
		init();
	}
	
	private void init() {
		Date date = Calendar.getInstance().getTime();
		String s = sdf.format(date);
		setPeriod1(s);
		setPeriod2(s);		
	}
	
	public void setTimeStamps() {
		try {
			periodTimeStamp1 =  sdf.parse(getPeriod1()).getTime() / 1000;
			periodTimeStamp2 =  sdf.parse(getPeriod2()).getTime() / 1000;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getStockTicker() {
		return stockTicker;
	}

	public void setStockTicker(String stockTicker) {
		this.stockTicker = stockTicker;
	}

	public String getPeriod1() {
		return period1;
	}

	public void setPeriod1(String period1) {
		this.period1 = period1;
	}

	public long getPeriodTimeStamp1() {
		return periodTimeStamp1;
	}

	public void setPeriodTimeStamp1(long periodTimeStamp1) {
		this.periodTimeStamp1 = periodTimeStamp1;
	}

	public String getPeriod2() {
		return period2;
	}

	public void setPeriod2(String period2) {
		this.period2 = period2;
	}

	public long getPeriodTimeStamp2() {
		return periodTimeStamp2;
	}

	public void setPeriodTimeStamp2(long periodTimeStamp2) {
		this.periodTimeStamp2 = periodTimeStamp2;
	}

	public String getInterval() {
		return interval;
	}

	public void setInterval(String interval) {
		this.interval = interval;
	}

	public String getEvents() {
		return events;
	}

	public void setEvents(String events) {
		this.events = events;
	}

	@Override
	public String toString() {
		return "QuoteDetailRequest [stockTicker=" + stockTicker + ", period1=" + period1 + ", period2=" + period2
				+ ", interval=" + interval + ", events=" + events + "]";
	}

}
