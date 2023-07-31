package org.llin.twelvequotes.controller.util;

import java.util.ArrayList;
import java.util.List;

import static org.llin.twelvequotes.controller.QuoteDetailRequestController.TABS_PER_LINE;
import org.llin.twelvequotes.model.AllQuotes;
import org.llin.twelvequotes.model.SingleQuote;

public class TabsUtil<T extends SingleQuote> {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T> void generateTabs(AllQuotes all_quotes) {
		List<List<String>> tabsOfTabs = new ArrayList<>();
		List<String> subTabs = new ArrayList<>();
		
		List<String> tabs = new ArrayList<>();
		SingleQuote s, ss;
		
		for (List<T> l : (List<List<T>>)all_quotes.getChunkedList()) {
			 s = (SingleQuote) l.get(0);
			 ss = (SingleQuote) l.get(l.size() - 1);			 
			 tabs.add(s.getSymbol() + "-" + ss.getSymbol());			 
		}
		
		int index = 0;
		for (String str : tabs) {
			if (index % TABS_PER_LINE == 0) {
				tabsOfTabs.add(subTabs);
				subTabs.clear();			
			}				
			subTabs.add(str);
			index++;
		}		
		tabsOfTabs.add(subTabs);
		
		all_quotes.setTabs(tabsOfTabs);
	}
	
}
