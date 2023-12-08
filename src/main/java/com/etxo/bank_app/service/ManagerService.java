package com.etxo.bank_app.service;

import com.etxo.bank_app.dto.ManagerDto;
import com.etxo.bank_app.entity.Manager;
import com.etxo.bank_app.reposi.ManagerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ManagerService {

    private final ManagerRepository repository;

    public ManagerDto create(ManagerDto dto){
        if (repository.existsByEmail(dto.getEmail())){
            throw new RuntimeException("A client with such an email already exists!");
        }
        ManagerDto savedManager = ManagerMapping.mapToDto(
                repository.save(ManagerMapping.mapToEntity(dto)));
        return savedManager;
    }

    public ManagerDto getManagerById(Long id){
        Manager manager = repository.findById(id).orElse(null);
        return manager == null ? null : ManagerMapping.mapToDto(manager);
    }
}
