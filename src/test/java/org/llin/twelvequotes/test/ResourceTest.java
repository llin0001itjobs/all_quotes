package org.llin.twelvequotes.test;

import org.junit.jupiter.api.Test;
import org.llin.twelvequotes.config.TwelveQuotesConfig;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ResourceTest {

	@Autowired
    private TwelveQuotesConfig quotesConfig;
	    
    @Test
    void yourTest() {
        System.out.println("Quotes Per Page: " + quotesConfig.getQuotesPerPage());
        System.out.println("Tabs Per Line: " + quotesConfig.getTabsPerLine());
        // Test code using quotesPerPage and tabsPerLine
    }
}
