package com.baeldung.boot.bootstrapsimpleapplication;

import com.baeldung.boot.bootstrapsimpleapplication.web.handler.RestExceptionHandler;
import com.baeldung.boot.bootstrapsimpleapplication.web.handler.RestTemplateResponseErrorHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@EnableJpaRepositories("com.baeldung.boot.bootstrapsimpleapplication.web.repository")
@EntityScan("com.baeldung.boot.bootstrapsimpleapplication.web.model")
@SpringBootApplication
public class BootstrapSimpleApplication {
	/*
	* Order of Evaluation
		As a final note, let’s look at the order in which these approaches are evaluated by Spring Boot.

		Basically, the configurations priority is:

		1. embedded server configuration
		2. command line arguments (java -jar spring-5.jar --server.port=8083/java -jar -Dserver.port=8083 spring-5.jar)
		3. property files
		4. main @SpringBootApplication configuration
	* */

	public static void main(String[] args) {
		//server.port argument will have Order 1
		SpringApplication app = new SpringApplication(BootstrapSimpleApplication.class);
		app.setDefaultProperties(Collections.singletonMap("server.port","8080"));
		app.run(args);
	}

	@Bean
	RestTemplate restTemplate(){
		return new RestTemplateBuilder()
				.errorHandler(new RestTemplateResponseErrorHandler())
				.build();
	}
}
