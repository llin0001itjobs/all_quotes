package org.llin.twelvequotes.util;

import java.io.IOException;
import java.util.ArrayList;

import org.llin.twelvequotes.Constants;
import org.llin.twelvequotes.model.AllQuotes;
import org.llin.twelvequotes.model.SingleQuote;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class QuotesRetriever<T extends SingleQuote> extends Constants {

	@Value("${api.twelve-quotes.url}")
	private String url;

	@Scheduled(cron = "0 0 3 * * ?")
	public AllQuotes<T> retrieve() {
		AllQuotes<T> all_quotes = new AllQuotes<>(new ArrayList<>());

		JsonUtilSingleQuote<SingleQuote> jsonUtil = new JsonUtilSingleQuote<>(url);
		try {
			all_quotes = new AllQuotes<>(jsonUtil.retrieveObject());
		} catch (IOException e) {
			e.printStackTrace();
		}

		all_quotes.populateCountrySet();

		return all_quotes;
	}

}
