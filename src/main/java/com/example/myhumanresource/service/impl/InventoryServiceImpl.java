package com.example.myhumanresource.service.impl;

import com.example.myhumanresource.dto.InventoryDto;
import com.example.myhumanresource.entity.Inventory;
import com.example.myhumanresource.exception.ResourceNotFoundException;
import com.example.myhumanresource.repository.InventoryRepository;
import com.example.myhumanresource.service.InventoryService;
import lombok.AllArgsConstructor;
import com.example.myhumanresource.mapper.InventoryMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private InventoryRepository inventoryRepository;

    @Override
    public InventoryDto createInventory(InventoryDto inventoryDto) {
        Inventory inventory= InventoryMapper.mapToInventory(inventoryDto);
        Inventory savedInventory=inventoryRepository.save(inventory);
        return InventoryMapper.mapToInventoryDto(savedInventory);
    }

    @Override
    public List<InventoryDto> getAllInventories() {
        List<Inventory> inventories = inventoryRepository.findAll();
        return inventories.stream().map((inventory) -> InventoryMapper.mapToInventoryDto(inventory))
                .collect(Collectors.toList());
    }

    @Override
    public InventoryDto getInventoryById(Long inventoryId) {
        Optional<Inventory> inventoryOptional = inventoryRepository.findById(inventoryId);
        Inventory inventory = inventoryOptional.orElseThrow(() ->
                new ResourceNotFoundException("Employee is not exist with given id" + inventoryId));
        return InventoryMapper.mapToInventoryDto(inventory);
    }

    @Override
    public List<InventoryDto> getInventoriesByBrand(String brand) {
        List<Inventory> inventoryList;
        if (brand != null && !brand.isEmpty()) {
            inventoryList = inventoryRepository.findByBrand(brand);
        } else {
            inventoryList = inventoryRepository.findAll();
        }

        return inventoryList.stream() // Bu sayede bir listenin her bir elemanını tek tek işleyebiliriz.
                .map(InventoryMapper::mapToInventoryDto)
                .collect(Collectors.toList()); //Stream üzerindeki sonuçları bir liste olarak toplar.
    }
    }




