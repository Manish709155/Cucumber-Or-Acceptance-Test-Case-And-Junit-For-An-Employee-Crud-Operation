package com.employee.crud.cucumber.testing.controller;


import com.employee.crud.cucumber.testing.helper.EmployeeHelper;
import com.employee.crud.cucumber.testing.request.EmployeeRequest;
import com.employee.crud.cucumber.testing.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/employees")
public class EmployeeRestController {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    EmployeeHelper employeeHelper;


    @PostMapping("/create")
    public ResponseEntity<Object> createEmployee(@RequestBody EmployeeRequest request){
        try{
          return  new ResponseEntity<>(employeeService.createEmployee(employeeHelper.getEntity(request)), HttpStatus.CREATED) ;
        }
        catch (RuntimeException exception){
            return  new ResponseEntity<>(exception.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
