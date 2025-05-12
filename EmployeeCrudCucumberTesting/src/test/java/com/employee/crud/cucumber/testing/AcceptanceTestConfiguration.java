package com.employee.crud.cucumber.testing;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
@EnableAutoConfiguration
@PropertySource("classpath:application.properties")
@ComponentScan({"com.employee.crud.cucumber.testing"})
@Import({EmployeeCrudCucumberTestingApplication.class})
public class AcceptanceTestConfiguration {
    @PostConstruct
    private void init(){
        RestAssured.defaultParser= Parser.JSON;
    }
}
