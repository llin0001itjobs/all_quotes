package org.llin.twelvequotes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;

@SpringBootApplication
@ConfigurationProperties(prefix = "api")
public class AllQoutesApplication {

	public static void main(String[] args) {
		SpringApplication.run(AllQoutesApplication.class, args);
	}

}
