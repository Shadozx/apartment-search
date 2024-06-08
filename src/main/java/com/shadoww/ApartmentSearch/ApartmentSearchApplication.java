package com.shadoww.ApartmentSearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ApartmentSearchApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApartmentSearchApplication.class, args);
	}

}
