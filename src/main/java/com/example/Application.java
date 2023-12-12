package com.example;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import lombok.RequiredArgsConstructor;

@SpringBootApplication
@RequiredArgsConstructor
public class Application {
	private final CustomerRepository customerRepository;

	private static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class);
	}

	@Bean
	public CommandLineRunner demo() {
		return (args) -> {
			this.customerRepository.create(new Customer("Jack", "Bauer"));
			this.customerRepository.create(new Customer("Chloe", "O'Brian"));
			this.customerRepository.create(new Customer("Kim", "Bauer"));
			this.customerRepository.create(new Customer("David", "Palmer"));
			this.customerRepository.create(new Customer("Michelle", "Dessler"));

			// fetch all customers
			log.info("Customers found with findAll():");
			log.info("-------------------------------");

			List<Customer> customerList = this.customerRepository.getCustomerList();
			customerList.forEach(c -> {
				log.info(c.toString());
			});
			log.info("");

			// fetch an individual customer by ID
			Customer customer = this.customerRepository.getCustomer(1L);
			log.info("Customer found with findById(1L):");
			log.info("--------------------------------");
			if (customer != null)
				log.info(customer.toString());
			log.info("");

			// fetch customers by last name
			customerList = this.customerRepository.getCustomerListByLastName("Bauer");
			log.info("Customer found with findByLastName('Bauer'):");
			log.info("--------------------------------------------");
			customerList.forEach(c -> {
				log.info(c.toString());
			});
			log.info("");
		};
	}
}