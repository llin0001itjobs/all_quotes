package org.llin.twelvequotes.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.llin.twelvequotes.util.LoggingAdvice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AllQuotes {
	private static final Logger logger = LoggerFactory.getLogger(LoggingAdvice.class);
	
	private List<SingleQuote> all;
	private List<SingleQuote> onlyOneCountry;
	private List<SingleQuote> onlyOneType;
	
	private String selectedCountry = "United States";
	private String selectedType = "";
	private String selectedSymbol = "";
	
	private Set<String> countrySet = new TreeSet<>();
	private Set<String> typeSet = new TreeSet<>();

	public AllQuotes(List<SingleQuote> quotes) {
		all = quotes;
	}

	public String getSelectedCountry() {
		return selectedCountry;
	}

	public void setSelectedCountry(String selectedCountry) {
		this.selectedCountry = selectedCountry;
	}

	public String getSelectedType() {
		return selectedType;
	}

	public void setSelectedType(String selectedType) {
		this.selectedType = selectedType;
	}

	public String getSelectedSymbol() {
		return selectedSymbol;
	}

	public void setSelectedSymbol(String selectedSymbol) {
		this.selectedSymbol = selectedSymbol;
	}
	
	public List<SingleQuote> getAll() {
		return all;
	}

	public List<SingleQuote> getOnlyOneCountry() {
		return onlyOneCountry;
	}
	
	public List<SingleQuote> getOnlyOneType() {
		return onlyOneType;
	}

	public Set<String> getCountrySet() {
		return countrySet;
	}

	public Set<String> getTypeSet() {
		return typeSet;
	}
	
	public void populateCountrySet() {
		List<String> list = new ArrayList<>();
				
		for (SingleQuote q : all) {		
			if (!q.getCountry().isEmpty()) {
				list.add(q.getCountry());
			}
		}
		countrySet = new TreeSet<>(list);
	}
	
	public void populateTypeSet() {
		List<String> list = new ArrayList<>();
		
		for (SingleQuote q : onlyOneCountry) {		
			list.add(q.getType());
		}
		typeSet = new TreeSet<>(list);	
	}
		
	public void populateForOnlySelectedCountry() {
		List<SingleQuote> quotes = new ArrayList<>();
		for (SingleQuote q : all) {
			if (q.getCountry().equals(selectedCountry)) {
				quotes.add(q);
			}
			onlyOneCountry = quotes;
		}
	}

	public void populateForOnlySelectedType() {
		List<SingleQuote> quotes = new ArrayList<>();
		for (SingleQuote q : onlyOneCountry) {
			if (q.getType().equals(selectedType)) {
				quotes.add(q);
			}
		}
		if (selectedType.trim().equals("")) {
			quotes = onlyOneCountry;
		}
		onlyOneType = quotes;	
		System.out.println(this);
	}

	@Override
	public String toString() {
		return "AllQuotes [all.size()=" + all.size() + ", onlyOneCountry=" + onlyOneCountry.size() + ", onlyOneType=" + onlyOneType.size() + ", selectedCountry=" + selectedCountry
				+ ", selectedType=" + selectedType + ", selectedSymbol=" + selectedSymbol + ", countrySet=" + countrySet
				+ ", typeSet=" + typeSet + "]";
	}
				
}
