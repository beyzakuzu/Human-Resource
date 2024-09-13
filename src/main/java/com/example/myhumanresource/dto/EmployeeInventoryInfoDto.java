package com.example.myhumanresource.dto;

import com.example.myhumanresource.entity.EmployeeInventoryInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeInventoryInfoDto {
    private Long id;
    private EmployeeDto employee;
    private InventoryDto inventory;
    private LocalDate dateOfReceipt;
    private String deliveredByPersonnel;
    private LocalDate returnDate;
    private String receivedByPersonnel;
    private String status;

    public EmployeeInventoryInfoDto(EmployeeInventoryInfo employeeInventoryInfo) {
        this.id = employeeInventoryInfo.getId();
        this.employee = new EmployeeDto(employeeInventoryInfo.getEmployee());
        this.inventory = new InventoryDto(employeeInventoryInfo.getInventory());
        this.dateOfReceipt = employeeInventoryInfo.getDateOfReceipt();
        this.deliveredByPersonnel = employeeInventoryInfo.getDeliveredByPersonnel();
        this.returnDate = employeeInventoryInfo.getReturnDate();
        this.receivedByPersonnel = employeeInventoryInfo.getReceivedByPersonnel();
        this.status = employeeInventoryInfo.getStatus().toString();
    }
}
