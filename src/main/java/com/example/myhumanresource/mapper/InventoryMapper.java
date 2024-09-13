package com.example.myhumanresource.mapper;

import com.example.myhumanresource.dto.EmployeeDto;
import com.example.myhumanresource.dto.InventoryDto;
import com.example.myhumanresource.entity.Employee;
import com.example.myhumanresource.entity.Inventory;

public class InventoryMapper {
    public static InventoryDto mapToInventoryDto(Inventory inventory) {
        return new InventoryDto(
                inventory.getId(),
                inventory.getType(),
                inventory.getInventoryEntryDate(),
                inventory.getBrand(),
                inventory.getModel(),
                inventory.getSerialNumber(),
                inventory.getStatus()

        );
    }

    public static Inventory mapToInventory(InventoryDto inventoryDto) {
       return new Inventory(
               inventoryDto.getId(),
               inventoryDto.getType(),
               inventoryDto.getInventoryEntryDate(),
               inventoryDto.getBrand(),
               inventoryDto.getModel(),
               inventoryDto.getSerialNumber(),
               inventoryDto.getStatus()
    );

    }

    }


