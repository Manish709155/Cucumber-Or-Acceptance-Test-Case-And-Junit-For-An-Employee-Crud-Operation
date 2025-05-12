package com.employee.crud.cucumber.testing.serviceImpl;

import com.employee.crud.cucumber.testing.entity.Employee;
import com.employee.crud.cucumber.testing.repository.EmployeeRepository;
import com.employee.crud.cucumber.testing.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployeeById(Long employeeId) {
        employeeRepository.deleteById(employeeId);
    }

    @Override
    public Optional<Employee> findByEmployeeId(Long employeeId) {
        return  employeeRepository.findById(employeeId);
    }

    @Override
    public void deleteEmployeesById(List<Long> empsId) {
        employeeRepository.deleteAllById(empsId);
    }

    @Override
    public List<Employee> getListOfAnEmployee() {
        return  employeeRepository.findAll();
    }

    @Override
    public Optional<Employee> updateEmployee(Long id, Employee updatedData) {
        return employeeRepository.findById(id).map(existing -> {
            existing.setName(updatedData.getName());
            existing.setDepartment(updatedData.getDepartment());
            return employeeRepository.save(existing);
        });
    }

}
