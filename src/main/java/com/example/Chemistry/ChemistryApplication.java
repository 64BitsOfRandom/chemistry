package com.example.Chemistry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ITemplateResolver;

@SpringBootApplication
public class ChemistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChemistryApplication.class, args);
	}

	@Bean
	public SpringTemplateEngine templateEngine(ITemplateResolver resolver, SpringSecurityDialect sec){
		final SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.setTemplateResolver(resolver);
		templateEngine.addDialect(sec); // Enable use of "sec"
		return templateEngine;
	}

}
