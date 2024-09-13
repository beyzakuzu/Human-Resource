package com.example.myhumanresource.service;


import com.example.myhumanresource.dto.EmployeeDto;
import com.example.myhumanresource.dto.InventoryDto;

import java.util.List;

public interface InventoryService {

    InventoryDto createInventory(InventoryDto inventoryDto);

    List<InventoryDto> getAllInventories();

    InventoryDto getInventoryById(Long inventoryId);

    public List<InventoryDto> getInventoriesByBrand(String brand);



}
