package com.example.myhumanresource.controller;

import com.example.myhumanresource.dto.EmployeeInventoryInfoDto;
import com.example.myhumanresource.entity.Inventory;
import com.example.myhumanresource.service.EmployeeInventoryInfoService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("/employee-inventory-info")
public class EmployeeInventoryInfoController {

    private final EmployeeInventoryInfoService employeeInventoryInfoService;

    @Autowired
    public EmployeeInventoryInfoController(EmployeeInventoryInfoService employeeInventoryInfoService) {
        this.employeeInventoryInfoService = employeeInventoryInfoService;
    }
    // Çalışana envanter ataması yapıyor
    @PostMapping("/assign")
    public ResponseEntity<String> assignInventoryToEmployee(
            @RequestParam Long employeeId,
            @RequestParam Long inventoryId,
            @RequestParam String deliveredByPersonnel
    ) {
        try {
            employeeInventoryInfoService.assignInventoryToEmployee(employeeId, inventoryId, deliveredByPersonnel);
            return ResponseEntity.ok("Inventory assigned to employee successfully.");
        } catch (IllegalStateException e) {
            // Envantter zaten dolu ise, hata mesajını geri döndür
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }
// Id ye göre envanter bilgisi getiriyor.
    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<EmployeeInventoryInfoDto>> getEmployeeInventoryInfoByEmployeeId(@PathVariable Long employeeId) {
        try {
            List<EmployeeInventoryInfoDto> employeeInventoryInfos = employeeInventoryInfoService.getEmployeeInventoryInfoByEmployeeId(employeeId);

            for(EmployeeInventoryInfoDto infoDto : employeeInventoryInfos) {
                infoDto.getInventory().setStatus(Inventory.InventoryStatus.DOLU);
            }
            return ResponseEntity.ok(employeeInventoryInfos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    //envanter durumunu boşa çekiyor
    @PutMapping("/return")
    public ResponseEntity<String> returnInventory(
            @RequestParam Long employeeInventoryInfoId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate returnDate
    ) {
        try {
            employeeInventoryInfoService.returnInventory(employeeInventoryInfoId, returnDate);
            return ResponseEntity.ok("Inventory returned successfully.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }

    // Boştaki envanterleri getiriyor.

    @GetMapping("/available")
    public ResponseEntity<List<Inventory>> getAvailableInventories() {
        try {
            List<Inventory> availableInventories = employeeInventoryInfoService.getAvailableInventories();
            return ResponseEntity.ok(availableInventories);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}







