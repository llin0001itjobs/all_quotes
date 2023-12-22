package org.llin.twelvequotes.model;

public enum Events {
	HISTORY ("history"),
	DIV ("div"),
	SPLIT ("split");

    private final String name;       

    private Events(String s) {
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
