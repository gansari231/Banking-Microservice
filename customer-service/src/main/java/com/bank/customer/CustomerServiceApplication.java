package com.bank.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Value;

@SpringBootApplication
public class CustomerServiceApplication {

	@Value("${test.message}")
	private String testMessage;

	public static void main(String[] args) {

		SpringApplication.run(CustomerServiceApplication.class, args);
	}
}
