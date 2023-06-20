package org.llin.twelvequotes.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class AllQuotes {
	private List<SingleQuote> quotes;

	private String selectedCountry = "United States";
	
	private Set<String>	currencySet;
	private Set<String>	exchangeSet;
	private Set<String>	mic_codeSet;
	private Set<String>	countrySet;
	private Set<String>	typeSet;

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

	public Set<String> getCurrencySet() {
		return currencySet;
	}

	public Set<String> getExchangeSet() {
		return exchangeSet;
	}

	public Set<String> getMic_codeSet() {
		return mic_codeSet;
	}

	public Set<String> getCountrySet() {
		return countrySet;
	}

	public Set<String> getTypeSet() {
		return typeSet;
	}

	public void populateSets() {
		List<String> lcur = new ArrayList<>();
		List<String> lexc = new ArrayList<>();
		List<String> lmic = new ArrayList<>();
		List<String> lcty = new ArrayList<>();
		List<String> ltyp = new ArrayList<>();

		for (SingleQuote q : quotes) {
			lcur.add(q.getCurrency());
			lexc.add(q.getExchange());
			lmic.add(q.getMic_code());
			if (!q.getCountry().isEmpty()) {
				lcty.add(q.getCountry());
			}
			ltyp.add(q.getType());			
		}
		
		currencySet = new TreeSet<>(lcur);
		exchangeSet = new TreeSet<>(lexc);
		mic_codeSet = new TreeSet<>(lmic);
		countrySet  = new TreeSet<>(lcty);
		typeSet     = new TreeSet<>(ltyp);
	}	
 
}
