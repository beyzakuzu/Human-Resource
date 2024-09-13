package com.example.myhumanresource.repository;

import com.example.myhumanresource.entity.Employee;
import com.example.myhumanresource.entity.EmployeeInventoryInfo;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeInventoryInfoRepository extends JpaRepository<EmployeeInventoryInfo,Long> {

    List<EmployeeInventoryInfo> findByEmployee(Employee employee);
}
