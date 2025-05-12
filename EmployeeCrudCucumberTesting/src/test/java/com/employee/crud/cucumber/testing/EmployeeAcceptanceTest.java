package com.employee.crud.cucumber.testing;

import com.employee.crud.cucumber.testing.entity.Employee;
import com.employee.crud.cucumber.testing.request.EmployeeRequest;
import com.employee.crud.cucumber.testing.service.EmployeeService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class EmployeeAcceptanceTest {
    private final TestRestTemplate restTemplate;

    private EmployeeRequest employeeRequest;
    private EmployeeRequest createdEmployee;
    private ResponseEntity<EmployeeRequest> response;

    private  Long empId;
    @Autowired
    EmployeeService employeeService;

    private  Employee employee;

    public EmployeeAcceptanceTest(TestRestTemplate restTemplate) {

        this.restTemplate = restTemplate;
    }

    @Given("I have employee details with name {string} and department {string}")
    public void i_have_employee_details(String name, String department) {
        employeeRequest = EmployeeRequest.builder()
                .name(name)
                .department(department)
                .build();
    }

    @When("I create the employee")
    public void i_create_the_employee() {
        response = restTemplate.postForEntity("/api/employees/create", employeeRequest, EmployeeRequest.class);
        createdEmployee = response.getBody();
    }

    @Then("the employee should be created successfully with name {string} and department {string}")
    public void the_employee_should_be_created_successfully(String name, String department) {
        assertNotNull(createdEmployee);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(name, createdEmployee.getName());
        assertEquals(department, createdEmployee.getDepartment());
    }

    // delete Employee by id
    @Given("an employee exist with this employee_id {string}")
    public void anEmployeeExistWithThisEmployee_id(String employeeId) {
       empId = Long.valueOf(employeeId);
    }
    @When("I delete the employee by ID")
    public void i_delete_the_employee_by_id() {
        employeeService.deleteEmployeeById(empId);
    }

    @Then("the employee should not exist anymore")
    public void the_employee_should_not_exist_anymore() {
        Optional<Employee> optionalEmployee = employeeService.findByEmployeeId(empId);
        Assertions.assertFalse(optionalEmployee.isPresent(), "Employee should have been deleted");
    }

    // update employee by employee id


    @Given("an employee with ID {long} already exists")
    public void an_employee_with_id_already_exists(Long id) {
        empId = id;
        Assertions.assertTrue(employeeService.findByEmployeeId(id).isPresent(),
                "Employee with ID " + id + " must exist before update");
    }

    @When("I update the employee name to {string} and department to {string}")
    public void i_update_the_employee_name_and_department(String newName, String newDept) {
        Optional<Employee> optionalEmployee = employeeService.findByEmployeeId(empId);
        Assertions.assertTrue(optionalEmployee.isPresent(), "Employee must exist for update");

        Employee employee = optionalEmployee.get();
        employee.setName(newName);
        employee.setDepartment(newDept);
        employeeService.createEmployee(employee);
    }

    @Then("the employee name should be {string} and department should be {string}")
    public void the_employee_should_have_updated_name_and_department(String expectedName, String expectedDept) {
        Optional<Employee> optionalEmployee = employeeService.findByEmployeeId(empId);
        Assertions.assertTrue(optionalEmployee.isPresent(), "Employee must exist after update");
        Employee updated = optionalEmployee.get();
        Assertions.assertEquals(expectedName, updated.getName());
        Assertions.assertEquals(expectedDept, updated.getDepartment());
    }
     // find by employee id
    @Given("an employee with ID {long} exists in the system")
    public void an_employee_with_id_exists(Long id) {
        empId = id;
        Assertions.assertTrue(employeeService.findByEmployeeId(id).isPresent(),
                "Employee with ID " + id + " must exist in the system");
    }

    @When("I retrieve the employee by ID")
    public void i_retrieve_the_employee_by_id() {
        Optional<Employee> emp =employeeService.findByEmployeeId(empId);
        Assertions.assertTrue(emp.isPresent(), "Employee not found");
        employee = emp.get();
    }

    @Then("the employee name should be {string} and department should be match {string}")
    public void theEmployeeNameShouldBeAndDepartmentShouldBeMatch(String expectedName, String expectedDept) {
        Assertions.assertNotNull(employee, "Employee should have been retrieved");
        Assertions.assertEquals(expectedName, employee.getName(), "Name mismatch");
        Assertions.assertEquals(expectedDept, employee.getDepartment(), "Department mismatch");
    }

}
