package com.example.myhumanresource.repository;

import com.example.myhumanresource.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InventoryRepository extends JpaRepository<Inventory,Long> {

        List<Inventory> findByStatus(Inventory.InventoryStatus status);
        List<Inventory> findByBrand(String type);


}
