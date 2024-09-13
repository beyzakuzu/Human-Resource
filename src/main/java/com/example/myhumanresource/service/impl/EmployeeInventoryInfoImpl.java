package com.example.myhumanresource.service.impl;

import com.example.myhumanresource.dto.EmployeeInventoryInfoDto;
import com.example.myhumanresource.entity.Employee;
import com.example.myhumanresource.entity.EmployeeInventoryInfo;
import com.example.myhumanresource.entity.Inventory;
import com.example.myhumanresource.repository.EmployeeInventoryInfoRepository;
import com.example.myhumanresource.repository.EmployeeRepository;
import com.example.myhumanresource.repository.InventoryRepository;
import com.example.myhumanresource.service.EmployeeInventoryInfoService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import com.example.myhumanresource.mapper.EmployeeInventoryInfoMapper;


@Service
public class EmployeeInventoryInfoImpl implements EmployeeInventoryInfoService {

    private final EmployeeRepository employeeRepository;
    private final InventoryRepository inventoryRepository;
    private final EmployeeInventoryInfoRepository employeeInventoryInfoRepository;

    @Autowired
    public EmployeeInventoryInfoImpl(EmployeeRepository employeeRepository,
                                     InventoryRepository inventoryRepository,
                                     EmployeeInventoryInfoRepository employeeInventoryInfoRepository) {
        this.employeeRepository = employeeRepository;
        this.inventoryRepository = inventoryRepository;
        this.employeeInventoryInfoRepository = employeeInventoryInfoRepository;
    }

    @Override
    public List<EmployeeInventoryInfoDto> getEmployeeInventoryInfoByEmployeeId(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElse(null);
        if (employee != null) {
            List<EmployeeInventoryInfo> employeeInventoryInfos = employeeInventoryInfoRepository.findByEmployee(employee);
            return employeeInventoryInfos.stream()
                    .map(EmployeeInventoryInfoMapper::mapToEmployeeInventoryInfoDto)
                    .collect(Collectors.toList());
        } else {
            return List.of();
        }
    }

    @Override
    public void assignInventoryToEmployee(Long employeeId, Long inventoryId, String deliveredByPersonnel) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found with id: " + employeeId));

        Inventory inventory = inventoryRepository.findById(inventoryId)
                .orElseThrow(() -> new EntityNotFoundException("Inventory not found with id: " + inventoryId));
        if (inventory.getStatus() == Inventory.InventoryStatus.DOLU) {
            // Envanter zaten dolu ise, hata mesajı fırlat
            throw new IllegalStateException("Inventory is already assigned to another employee.");
        }


        EmployeeInventoryInfo employeeInventoryInfo = new EmployeeInventoryInfo();
        employeeInventoryInfo.setEmployee(employee);
        employeeInventoryInfo.setInventory(inventory);
        employeeInventoryInfo.setDateOfReceipt(LocalDate.now());
        employeeInventoryInfo.setDeliveredByPersonnel(deliveredByPersonnel);
        inventory.setStatus(Inventory.InventoryStatus.DOLU);
        inventoryRepository.save(inventory);
        employeeInventoryInfo.setStatus(EmployeeInventoryInfo.EnvanterStatu.DOLU);

        employeeInventoryInfoRepository.save(employeeInventoryInfo);
    }

    // Geri verilen envanteri boşa çek

    @Override
    public void returnInventory(Long employeeInventoryInfoId, LocalDate returnDate) {
        EmployeeInventoryInfo employeeInventoryInfo = employeeInventoryInfoRepository.findById(employeeInventoryInfoId)
                .orElseThrow(() -> new EntityNotFoundException("EmployeeInventoryInfo not found with id: " + employeeInventoryInfoId));

        Inventory inventory = employeeInventoryInfo.getInventory();

        // Envantterin statüsünü "BOŞTA" olarak güncelle
        inventory.setStatus(Inventory.InventoryStatus.BOSTA);

        // Teslim alınan tarihi ayarla
        employeeInventoryInfo.setReturnDate(returnDate);

        // Güncellenmiş envanteri kaydet
        inventoryRepository.save(inventory);
    }

    // Boştaki envanterleri getirme
    @Override
    public List<Inventory> getAvailableInventories() {
        return inventoryRepository.findByStatus(Inventory.InventoryStatus.BOSTA);
    }
}








