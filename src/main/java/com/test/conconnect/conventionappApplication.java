package com.test.conconnect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

/**
 * The main class for the Convention Application.
 */
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class conventionappApplication {

	/**
	 * The main method to start the application.
	 *
	 * @param args The command line arguments passed to the application.
	 */
	public static void main(String[] args) {
		SpringApplication.run(conventionappApplication.class, args);
	}

}
