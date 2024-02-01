package org.llin.twelvequotes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ConfigurationProperties(prefix = "api.twelve-quotes.presentation")
@EnableScheduling
public class AllQoutesApplication {

	public static void main(String[] args) {
		SpringApplication.run(AllQoutesApplication.class, args);
	}

}
