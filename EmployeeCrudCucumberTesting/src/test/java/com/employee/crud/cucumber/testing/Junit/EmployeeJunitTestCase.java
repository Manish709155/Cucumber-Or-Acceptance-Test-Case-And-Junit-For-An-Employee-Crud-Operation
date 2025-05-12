package com.employee.crud.cucumber.testing.Junit;

import com.employee.crud.cucumber.testing.entity.Employee;
import com.employee.crud.cucumber.testing.repository.EmployeeRepository;
import com.employee.crud.cucumber.testing.serviceImpl.EmployeeServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("Employee Service JUnit Tests")
@ExtendWith(MockitoExtension.class)
public class EmployeeJunitTestCase {

    private static final String EMP_NAME = "Manish Kumar";
    private static final String EMP_DEPARTMENT = "Development";

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Test
    @DisplayName("should be able to create an employee")
    void testCreateAnEmployee() {
        // Arrange
        Employee employee = new Employee();
        employee.setName(EMP_NAME);
        employee.setDepartment(EMP_DEPARTMENT);
        Employee savedEmployee = new Employee();
        savedEmployee.setId(1L);
        savedEmployee.setName(EMP_NAME);
        savedEmployee.setDepartment(EMP_DEPARTMENT);
        when(employeeRepository.save(employee)).thenReturn(savedEmployee);

        // Act
        Employee result = employeeService.createEmployee(employee);

        // Assert
        assertNotNull(result, "Created employee should not be null");
        assertEquals(1L, result.getId());
        assertEquals(EMP_NAME, result.getName());
        assertEquals(EMP_DEPARTMENT, result.getDepartment());
        verify(employeeRepository, times(1)).save(employee);
    }

    @Test
    @DisplayName("should be able to fetch an employee by ID")
    void testEmployeeById() {
        // Arrange
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setName(EMP_NAME);
        employee.setDepartment(EMP_DEPARTMENT);

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

        // Act
        Employee extractedEmp = employeeService.findByEmployeeId(1L).orElse(null);

        // Assert
        assertNotNull(extractedEmp);
        assertEquals(1L, extractedEmp.getId());
        assertEquals(EMP_NAME, extractedEmp.getName());
        assertEquals(EMP_DEPARTMENT, extractedEmp.getDepartment());

        verify(employeeRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("should update all fields of an existing employee")
    void testUpdateEmployeeAllFields() {
        // Here I am checking Existing employee in our configured DB
        Employee existing = new Employee();
        existing.setId(1L);
        existing.setName("Jane Smith");
        existing.setDepartment("Marketing");

        // Here updating New data to update
        Employee updatedData = new Employee();
        updatedData.setName(EMP_NAME);
        updatedData.setDepartment(EMP_DEPARTMENT);

        // Here Updated employee after save
        Employee updatedEmployee = new Employee();
        updatedEmployee.setId(1L);
        updatedEmployee.setName(EMP_NAME);
        updatedEmployee.setDepartment(EMP_DEPARTMENT);

        // Stub repository behavior
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(employeeRepository.save(any(Employee.class))).thenReturn(updatedEmployee);

        // Act
        Optional<Employee> resultOpt = employeeService.updateEmployee(1L, updatedData);

        // Assert
        assertTrue(resultOpt.isPresent());
        Employee result = resultOpt.get();
        assertEquals(1L, result.getId());
        assertEquals(EMP_NAME, result.getName());
        assertEquals(EMP_DEPARTMENT, result.getDepartment());

        // Verify repository calls
        verify(employeeRepository).findById(1L);
        verify(employeeRepository).save(existing);
    }

    @Test
    @DisplayName("should delete an employee by ID")
    void testDeleteEmployeeById() {
        Long employeeId = 1L;

        // No need to mock deleteById as it returns void, just verify interaction
        doNothing().when(employeeRepository).deleteById(employeeId);

        // Act
        employeeService.deleteEmployeeById(employeeId);

        // Assert - verify method was called once with correct ID
        verify(employeeRepository, times(1)).deleteById(employeeId);
    }

    @Test
    @DisplayName("should return a list of all employees")
    void testGetAllEmployees() {
        // Arrange - Create mock employee objects using setters
        Employee emp1 = new Employee();
        emp1.setId(1L);
        emp1.setName("Manish Kumar");
        emp1.setDepartment("Development");

        Employee emp2 = new Employee();
        emp2.setId(2L);
        emp2.setName("Jane Doe");
        emp2.setDepartment("HR");

        List<Employee> mockList = Arrays.asList(emp1, emp2);

        // Stub repository
        when(employeeRepository.findAll()).thenReturn(mockList);

        // Act
        List<Employee> resultList = employeeService.getListOfAnEmployee();

        // Assert
        assertNotNull(resultList);
        assertEquals(2, resultList.size());
        assertEquals("Manish Kumar", resultList.get(0).getName());
        assertEquals("HR", resultList.get(1).getDepartment());

        // Verify interaction with repository
        verify(employeeRepository, times(1)).findAll();
    }


}
