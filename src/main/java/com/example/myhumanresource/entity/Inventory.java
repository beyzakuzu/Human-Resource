package com.example.myhumanresource.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name= "inventories")
public class Inventory {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String type;
        private LocalDate inventoryEntryDate;
        private String brand;
        private String model;
        private String serialNumber;
        @Enumerated(EnumType.STRING)
        private InventoryStatus status;

         @OneToMany(mappedBy = "inventory", cascade = CascadeType.ALL)
        private List<EmployeeInventoryInfo> inventoryInfos;

    public Inventory(Long id, String type, LocalDate inventoryEntryDate, String brand, String model, String serialNumber, InventoryStatus status) {
        this.id = id;
        this.type = type;
        this.inventoryEntryDate = inventoryEntryDate;
        this.brand = brand;
        this.model = model;
        this.serialNumber = serialNumber;
        this.status = status;
    }

    public Inventory(Long id, String type, LocalDate inventoryEntryDate, String brand, String model, String serialNumber, String status) {
    }


    public enum InventoryStatus {
            BOSTA,
            DOLU
        }
    }

