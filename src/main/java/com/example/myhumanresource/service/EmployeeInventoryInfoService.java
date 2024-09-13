package com.example.myhumanresource.service;

import com.example.myhumanresource.dto.EmployeeInventoryInfoDto;
import com.example.myhumanresource.entity.Inventory;

import java.time.LocalDate;
import java.util.List;

public interface EmployeeInventoryInfoService {


    List<EmployeeInventoryInfoDto> getEmployeeInventoryInfoByEmployeeId(Long employeeId);
    void assignInventoryToEmployee(Long employeeId, Long inventoryId, String deliveredByPersonnel);
    public void returnInventory(Long employeeInventoryInfoId, LocalDate returnDate);

    public List<Inventory> getAvailableInventories();






}
