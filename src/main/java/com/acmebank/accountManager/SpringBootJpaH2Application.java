package com.acmebank.accountManager;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.acmebank.accountManager.model.Account;
import com.acmebank.accountManager.repository.AccountRepository;



@SpringBootApplication
public class SpringBootJpaH2Application {
	@Autowired
	private AccountRepository accountRepo;
	
	@Bean
	CommandLineRunner runner(){
		return args -> {
			// Save demo data to database
			accountRepo.save(new Account("12345678", new BigDecimal("1000000")));
			accountRepo.save(new Account("88888888", new BigDecimal("1000000")));

		};
	}
	
	private static final Logger logger = LoggerFactory.getLogger(SpringBootJpaH2Application.class);
	public static void main(String[] args) {
		SpringApplication.run(SpringBootJpaH2Application.class, args);
		logger.info("Hello Spring Boot");
	}

}
