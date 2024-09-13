package com.example.myhumanresource.service.impl;

import com.example.myhumanresource.dto.EmployeeDto;
import com.example.myhumanresource.entity.Employee;
import com.example.myhumanresource.exception.ResourceNotFoundException;
import com.example.myhumanresource.repository.EmployeeRepository;
import com.example.myhumanresource.service.EmployeeService;
import lombok.AllArgsConstructor;
import com.example.myhumanresource.mapper.EmployeeMapper;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
        Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);
        Employee employee = employeeOptional.orElseThrow(() ->
                new ResourceNotFoundException("Employee is not exist with given id" + employeeId));

        return EmployeeMapper.mapToEmployeeDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map((employee) -> EmployeeMapper.mapToEmployeeDto(employee))
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updatedEmployee) {

        Employee employee = employeeRepository.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException("Employee is not exists with given id" + employeeId)

        );
        employee.setFirstName(updatedEmployee.getFirstName());
        employee.setLastName(updatedEmployee.getLastName());
        employee.setTcNo(updatedEmployee.getTcNo());
        employee.setIsEmployed(updatedEmployee.getIsEmployed());
        employee.setRegistrationNo(updatedEmployee.getRegistrationNo());
        employee.setPhoto(updatedEmployee.getPhoto());
        employee.setGender(updatedEmployee.getGender());
        employee.setGraduation(updatedEmployee.getGraduation());
        employee.setMaritalStatus(updatedEmployee.getMaritalStatus());

        Employee updatedEmployeeObj = employeeRepository.save(employee);

        return EmployeeMapper.mapToEmployeeDto(updatedEmployeeObj);
    }

    @Override
    public void deleteEmployee(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException("Employee is not exists with given id" + employeeId)

        );
        employeeRepository.deleteById(employeeId);
    }



    @Override
    public List<EmployeeDto> filterEmployees(String firstName, String lastName) {
        List<Employee> filteredEmployees = employeeRepository.findByFirstNameContainingAndLastNameContaining(firstName, lastName);
        return filteredEmployees.stream().map(EmployeeDto::new).collect(Collectors.toList());
    }
}