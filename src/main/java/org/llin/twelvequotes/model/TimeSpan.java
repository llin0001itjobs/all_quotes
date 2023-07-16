package org.llin.twelvequotes.model;

public enum TimeSpan {
	MINUTE("minute"), 
	HOUR("hour"), 
	DAY("day"), 
	WEEK("week"), 
	MONTH("month"),
	QUARTER("quarter"),
	YEAR("year");
	
	private final String description;
	
	TimeSpan(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
	
}
