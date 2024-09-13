package com.example.myhumanresource.mapper;

import com.example.myhumanresource.dto.EmployeeInventoryInfoDto;
import com.example.myhumanresource.entity.EmployeeInventoryInfo;

public class EmployeeInventoryInfoMapper {

    public static EmployeeInventoryInfoDto mapToEmployeeInventoryInfoDto(EmployeeInventoryInfo employeeInventoryInfo){
        return new EmployeeInventoryInfoDto(
                employeeInventoryInfo.getId(),
                EmployeeMapper.mapToEmployeeDto(employeeInventoryInfo.getEmployee()),
                InventoryMapper.mapToInventoryDto(employeeInventoryInfo.getInventory()),
                employeeInventoryInfo.getDateOfReceipt(),
                employeeInventoryInfo.getDeliveredByPersonnel(),
                employeeInventoryInfo.getReturnDate(),
                employeeInventoryInfo.getReceivedByPersonnel(),
                employeeInventoryInfo.getStatus().toString()
        );
    }

    public static EmployeeInventoryInfo mapToEmployeeInventoryInfo(EmployeeInventoryInfoDto employeeInventoryInfoDto){
        return new EmployeeInventoryInfo(
                employeeInventoryInfoDto.getId(),
                EmployeeMapper.mapToEmployee(employeeInventoryInfoDto.getEmployee()),
                InventoryMapper.mapToInventory(employeeInventoryInfoDto.getInventory()),
                employeeInventoryInfoDto.getDateOfReceipt(),
                employeeInventoryInfoDto.getDeliveredByPersonnel(),
                employeeInventoryInfoDto.getReturnDate(),
                employeeInventoryInfoDto.getReceivedByPersonnel(),
                EmployeeInventoryInfo.EnvanterStatu.valueOf(employeeInventoryInfoDto.getStatus())
        );
    }
}


