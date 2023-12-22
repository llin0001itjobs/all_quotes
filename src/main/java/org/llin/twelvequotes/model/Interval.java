package org.llin.twelvequotes.model;

public enum Interval {
	ONE_DAY ("1d"),
	ONE_WEEK ("1wk"),
	ONE_MONTH ("1mo");

    private final String name;       

    private Interval(String s) {
        name = s;
    }

    public boolean equalsName(String otherName) {
        // (otherName == null) check is not needed because name.equals(null) returns false 
        return name.equals(otherName);
    }

    public String toString() {
       return this.name;
    }
}
