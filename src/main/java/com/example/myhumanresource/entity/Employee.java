package com.example.myhumanresource.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name= "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name= "first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="tc_no")
    private String tcNo;

    @Column(name="is_employed")
    private Boolean isEmployed;

    @Column(name="registration_no")
    private String registrationNo;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<EmployeeInventoryInfo> inventoryInfos;

 

    

    @Lob
    @Column(name="photo")
    private byte[] photo;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    public Employee(Long id, String firstName, String lastName, String tcNo, Boolean isEmployed, String registrationNo, byte[] photo, Gender gender, Graduation graduation, MaritalStatus maritalStatus, PositionName positionName, DepartmentName departmentName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.tcNo = tcNo;
        this.isEmployed = isEmployed;
        this.registrationNo = registrationNo;
        this.photo = photo;
        this.gender = gender;
        this.graduation = graduation;
        this.maritalStatus = maritalStatus;
        this.positionName = positionName;
        this.departmentName = departmentName;
    }


    public enum Gender {
        ERKEK,
        KADIN
    }

    @Enumerated(EnumType.STRING)
    private Graduation graduation;
    public enum Graduation{
        LISANS,
        ONLISANS,
        YUKSEKLISANS,
        DOKTORA
    }

    @Enumerated(EnumType.STRING)
    private MaritalStatus maritalStatus;
    public enum MaritalStatus {
        BEKAR,
        EVLI
    }
    @Enumerated(EnumType.STRING)
    private PositionName positionName;
    public enum PositionName {
        YAZILIM_GELISTIRME_UZMANI,
        YONETMEN,
        YONETMEN_YARDIMCISI;
    }

    @Enumerated(EnumType.STRING)
    private DepartmentName departmentName;
    public enum DepartmentName {
        SOFTWARE_DEVELOPMENT,
        R_AND_D;
    }





}
