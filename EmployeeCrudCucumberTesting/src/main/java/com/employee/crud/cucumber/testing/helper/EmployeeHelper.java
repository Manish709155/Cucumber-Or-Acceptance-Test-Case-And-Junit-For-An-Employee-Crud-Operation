package com.employee.crud.cucumber.testing.helper;

import com.employee.crud.cucumber.testing.entity.Employee;
import com.employee.crud.cucumber.testing.request.EmployeeRequest;
import org.springframework.stereotype.Component;

@Component
public class EmployeeHelper {

    public Employee getEntity(EmployeeRequest request){

        Employee employee = new Employee();

        employee.setName(request.getName());
        employee.setDepartment(request.getDepartment());

        return  employee;
    }
}
