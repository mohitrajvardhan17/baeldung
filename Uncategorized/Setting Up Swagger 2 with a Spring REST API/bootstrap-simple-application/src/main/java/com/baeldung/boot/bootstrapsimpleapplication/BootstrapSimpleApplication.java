package com.baeldung.boot.bootstrapsimpleapplication;

import com.baeldung.boot.bootstrapsimpleapplication.web.handler.RestTemplateResponseErrorHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

@EnableJpaRepositories("com.baeldung.boot.bootstrapsimpleapplication.web.repository")
@EntityScan("com.baeldung.boot.bootstrapsimpleapplication.web.model")
@SpringBootApplication
public class BootstrapSimpleApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootstrapSimpleApplication.class, args);
	}

	@Bean
	RestTemplate restTemplate(){
		return new RestTemplateBuilder()
				.errorHandler(new RestTemplateResponseErrorHandler())
				.build();
	}
}
