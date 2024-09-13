package com.example.myhumanresource.repository;

import com.example.myhumanresource.dto.EmployeeDto;
import com.example.myhumanresource.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    List<Employee> findByFirstNameContainingAndLastNameContaining(String firstName, String lastName);

}
