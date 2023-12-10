package com.etxo.bank_app.service;

import com.etxo.bank_app.dto.ManagerDto;
import com.etxo.bank_app.entity.Manager;
import com.etxo.bank_app.reposi.ManagerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ManagerService {

    private final ManagerRepository repository;

    public ManagerDto create(ManagerDto dto){
        if (repository.existsByEmail(dto.getEmail())){
            throw new RuntimeException("manager with such an email already exists!");
        }
        ManagerDto savedManager = ManagerMapping.mapToDto(
                repository.save(ManagerMapping.mapToEntity(dto)));
        return savedManager;
    }

    public ManagerDto getManagerById(Long id){
        Manager manager = repository.findById(id).orElse(null);
        return manager == null ? null : ManagerMapping.mapToDto(manager);
    }

    public Set<ManagerDto> getAll(){

        return new HashSet<>(repository.findAll().stream()
                .map(ManagerMapping::mapToDto)
                .toList());
    }

    public ManagerDto update(Long id, ManagerDto dto){
        Manager entity = repository.findById(id).orElse(null);
        return ManagerMapping.mapToDto(
                repository.save(ManagerMapping.mapToEntityUpdate(entity, dto)));
    }

    public ManagerDto managerTrigger(){
        Long randomId = 1L + new Random().nextLong(getAll().size());
        System.out.println(randomId);
        Manager entity = repository.findById(randomId).orElse(null);
        return ManagerMapping.mapToDto(entity);
    }
}
