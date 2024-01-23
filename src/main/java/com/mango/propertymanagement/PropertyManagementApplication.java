package com.mango.propertymanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PropertyManagementApplication {

	public static void main(String[] args) {
//		System.setProperty("spring.devtools.restart.enabled", "true");
		SpringApplication.run(PropertyManagementApplication.class, args);
	}

}
