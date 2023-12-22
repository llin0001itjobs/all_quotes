package org.llin.twelvequotes.model;

public class QuoteDetailResponse {

	private String date;
	private double open;
	private double high;
	private double low;
	private double close;
	private double adjustedClose;
	private double volume;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public double getOpen() {
		return open;
	}

	public void setOpen(double open) {
		this.open = open;
	}

	public double getHigh() {
		return high;
	}

	public void setHigh(double high) {
		this.high = high;
	}

	public double getLow() {
		return low;
	}

	public void setLow(double low) {
		this.low = low;
	}

	public double getClose() {
		return close;
	}

	public void setClose(double close) {
		this.close = close;
	}

	public double getAdjustedClose() {
		return adjustedClose;
	}

	public void setAdjustedClose(double adjustedClose) {
		this.adjustedClose = adjustedClose;
	}

	public double getVolume() {
		return volume;
	}

	public void setVolume(double volume) {
		this.volume = volume;
	}

	@Override
	public String toString() {
		return "SingleQuoteDetailResponse [date=" + date + ", open=" + open + ", high=" + high + ", low=" + low
				+ ", close=" + close + ", adjustedClose=" + adjustedClose + ", volume=" + volume + "]";
	}

}
