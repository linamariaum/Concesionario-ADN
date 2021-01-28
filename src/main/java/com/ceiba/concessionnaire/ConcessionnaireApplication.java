package com.ceiba.concessionnaire;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.ceiba.concessionnaire")
public class ConcessionnaireApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConcessionnaireApplication.class, args);
	}

}
