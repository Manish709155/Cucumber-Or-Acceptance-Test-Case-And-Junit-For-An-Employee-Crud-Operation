package com.employee.crud.cucumber.testing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.employee.crud.cucumber.testing")
public class EmployeeCrudCucumberTestingApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeCrudCucumberTestingApplication.class, args);
	}

}
