package com.employee.crud.cucumber.testing.repository;

import com.employee.crud.cucumber.testing.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Modifying
    @Transactional
    @Query("UPDATE Employee e SET e.name = :name, e.department = :department WHERE e.id = :id")
    Optional<Employee> updateEmployeeById(Long id, String name, String department);
}
