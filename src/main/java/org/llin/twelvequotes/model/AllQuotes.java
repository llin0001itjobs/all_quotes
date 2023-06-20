package org.llin.twelvequotes.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.stereotype.Component;

@Component
public class AllQuotes {
	private List<SingleQuote> quotes;

	private String selectedCountry = "United States";

	private Set<String> countrySet;
	private Set<String> typeSet;

	public AllQuotes(List<SingleQuote> quotes) {
		this.quotes = quotes;
	}

	public String getSelectedCountry() {
		return selectedCountry;
	}

	public void setSelectedCountry(String selectedCountry) {
		this.selectedCountry = selectedCountry;
	}

	public List<SingleQuote> getQuotes() {
		return quotes;
	}

	public Set<String> getCountrySet() {
		return countrySet;
	}

	public Set<String> getTypeSet() {
		return typeSet;
	}

	public void populateSets() {
		List<String> lcty = new ArrayList<>();
		List<String> ltyp = new ArrayList<>();

		for (SingleQuote q : quotes) {
			if (!q.getCountry().isEmpty()) {
				lcty.add(q.getCountry());
			}
			ltyp.add(q.getType());
		}

		countrySet = new TreeSet<>(lcty);
		typeSet = new TreeSet<>(ltyp);
	}

}
