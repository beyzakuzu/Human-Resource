package com.example.myhumanresource.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name= "employee_inventory_info")

public class EmployeeInventoryInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

     @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "inventory_id")
    private Inventory inventory;

    private LocalDate dateOfReceipt;
    private String deliveredByPersonnel;
    private LocalDate returnDate;
    private String receivedByPersonnel;

    @Enumerated(EnumType.STRING)
    private EnvanterStatu status;
    public enum EnvanterStatu {
        BOSTA,
        DOLU
    }


}
