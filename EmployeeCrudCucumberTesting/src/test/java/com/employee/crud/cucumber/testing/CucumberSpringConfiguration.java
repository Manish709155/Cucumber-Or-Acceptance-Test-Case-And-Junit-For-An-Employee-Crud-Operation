package com.employee.crud.cucumber.testing;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,classes = {EmployeeCrudCucumberTestingApplication.class})
public class CucumberSpringConfiguration {

}
