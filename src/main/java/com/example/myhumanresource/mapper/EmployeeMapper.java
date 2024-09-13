package com.example.myhumanresource.mapper;

import com.example.myhumanresource.dto.EmployeeDto;
import com.example.myhumanresource.entity.Employee;

public class EmployeeMapper {

    public static EmployeeDto mapToEmployeeDto(Employee employee){
        return new EmployeeDto(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getTcNo(),
                employee.getIsEmployed(),
                employee.getRegistrationNo(),
                employee.getPhoto(),
                employee.getGender(),
                employee.getGraduation(),
                employee.getMaritalStatus(),
                employee.getDepartmentName(),
                employee.getPositionName()

        );

    }
    public static Employee mapToEmployee(EmployeeDto employeeDto){
        return new Employee(
                employeeDto.getId(),
                employeeDto.getFirstName(),
                employeeDto.getLastName(),
                employeeDto.getTcNo(),
                employeeDto.getIsEmployed(),
                employeeDto.getRegistrationNo(),
                employeeDto.getPhoto(),
                employeeDto.getGender(),
                employeeDto.getGraduation(),
                employeeDto.getMaritalStatus(),
                employeeDto.getPositionName(),
                employeeDto.getDepartmentName()



        );
    }
}
