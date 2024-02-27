package com.test.conventionapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class conventionappApplication {

	public static void main(String[] args) {
		SpringApplication.run(conventionappApplication.class, args);
	}

}
