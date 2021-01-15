package com.example.Chemistry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class ChemistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChemistryApplication.class, args);
	}


}
