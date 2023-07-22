package org.llin.twelvequotes.model;

public enum TimeSpan {
	MINUTE("minute"), 
	HOUR("hour"), 
	DAY("day"), 
	WEEK("week"), 
	MONTH("month"),
	QUARTER("quarter"),
	YEAR("year");
	
	private String unit;
	
	TimeSpan(String unit) {
		this.unit = unit;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	
}
