package org.llin.twelvequotes.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "api.twelve-quotes.presentation")
public class TwelveQuotesConfig {

    @Value("${api.twelve-quotes.presentation.quotesPerPage}")
    private int quotesPerPage;
    
    @Value("${api.twelve-quotes.presentation.tabsPerLine}")
    private int tabsPerLine;

	public int getQuotesPerPage() {
		return quotesPerPage;
	}

	public int getTabsPerLine() {
		return tabsPerLine;
	}
    
	public void printOut() {
		System.out.println("quotesPerPage: " + quotesPerPage);
		System.out.println("tabsPerLine: " + tabsPerLine);		
	}
	
}
