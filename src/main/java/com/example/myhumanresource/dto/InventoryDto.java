package com.example.myhumanresource.dto;

import com.example.myhumanresource.entity.Inventory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor


public class InventoryDto {
        private Long id;
        private String type;
        private LocalDate inventoryEntryDate;
        private String brand;
        private String model;
        private String serialNumber;
        private Inventory.InventoryStatus status;

        public String getStatus() {
                return status.toString();
        }
        public InventoryDto(Inventory inventory) {
        }
}


