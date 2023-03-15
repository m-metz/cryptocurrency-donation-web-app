package com.group13.cryptocurrencywebapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class CryptocurrencywebappApplication {

	public static void main(String[] args) {
		SpringApplication.run(CryptocurrencywebappApplication.class, args);
	}

	@Bean
	public RestTemplate restRemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

}
