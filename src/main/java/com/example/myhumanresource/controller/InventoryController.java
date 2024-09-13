package com.example.myhumanresource.controller;

import com.example.myhumanresource.dto.EmployeeDto;
import com.example.myhumanresource.dto.InventoryDto;
import com.example.myhumanresource.service.InventoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("/api/inventories")

public class InventoryController {

    private InventoryService inventoryService;

    @PostMapping
    public ResponseEntity<InventoryDto> createInventory(@RequestBody InventoryDto inventoryDto){
        InventoryDto savedInventory = inventoryService.createInventory(inventoryDto);
        return new ResponseEntity<>(savedInventory, HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<List<InventoryDto>> getInventoriesByType(@RequestParam(required = false) String brand) {
        List<InventoryDto> inventoryList;
        if (brand != null && !brand.isEmpty()) {
            inventoryList = inventoryService.getInventoriesByBrand(brand);
        } else {
            inventoryList = inventoryService.getAllInventories();
        }
        return new ResponseEntity<>(inventoryList, HttpStatus.OK);
    }
}
