package com.example.myhumanresource.dto;

import com.example.myhumanresource.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String tcNo;
    private Boolean isEmployed;
    private String registrationNo;
    private byte[] photo;
    private Employee.Gender gender;
    private Employee.Graduation graduation;
    private Employee.MaritalStatus maritalStatus;
    private Employee.DepartmentName departmentName;
    private Employee.PositionName positionName;


    public EmployeeDto(Employee employee) {
        this.id = employee.getId();
        this.firstName = employee.getFirstName();
        this.lastName = employee.getLastName();
        this.tcNo = employee.getTcNo();
        this.isEmployed = employee.getIsEmployed();
        this.registrationNo = employee.getRegistrationNo();
        this.photo = employee.getPhoto();
        this.gender = employee.getGender();
        this.graduation = employee.getGraduation();
        this.maritalStatus = employee.getMaritalStatus();
        this.departmentName = employee.getDepartmentName();
        this.positionName = employee.getPositionName();
    }
}
