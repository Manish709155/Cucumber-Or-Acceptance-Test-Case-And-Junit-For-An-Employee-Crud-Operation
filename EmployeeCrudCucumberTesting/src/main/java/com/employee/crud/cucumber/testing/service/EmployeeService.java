package com.employee.crud.cucumber.testing.service;

import com.employee.crud.cucumber.testing.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

   Employee createEmployee(Employee employee);

    void deleteEmployeeById(Long employeeId);

    Optional<Employee> findByEmployeeId(Long employeeId);

    void  deleteEmployeesById(List<Long> empsId);

    List<Employee> getListOfAnEmployee();

    Optional<Employee>  updateEmployee(Long employeeId, Employee employee);
}
