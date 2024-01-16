package com.etxo.bank_app.service;

import com.etxo.bank_app.dto.ManagerDto;
import com.etxo.bank_app.entity.Manager;
import com.etxo.bank_app.exceptions.EmailExistsException;
import com.etxo.bank_app.exceptions.ManagerNotFoundException;
import com.etxo.bank_app.mapping.ManagerMapping;
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
    private final ManagerMapping mapper;
    public ManagerDto create(ManagerDto dto)
                            throws EmailExistsException{

        if (repository.existsByEmail(dto.getEmail())){
            throw new EmailExistsException(
                    "Manager with this email already exists!");
        }
        return mapper.mapToDto(repository
                .save(mapper.mapToEntity(dto)));
    }

    public ManagerDto getManagerById(Long id)
            throws ManagerNotFoundException{

        Manager manager = repository.findById(id)
                .orElseThrow(() -> new ManagerNotFoundException(
                        String.format("NO MANAGER with ID: %s", id)));

        return mapper.mapToDto(manager);
    }

    public Set<ManagerDto> getAll(){

        return new HashSet<>(repository.findAll().stream()
                .map(mapper::mapToDto)
                .toList());
    }

    public ManagerDto update(Long id, ManagerDto dto){

        Manager entity = repository.findById(id)
                .orElseThrow(() -> new ManagerNotFoundException(
                        String.format("NO MANAGER with ID: %s", id)));

        return mapper.mapToDto(
                repository.save(mapper.mapToEntityUpdate(entity, dto)));
    }

    //this method triggers a random manager from a list of the existing by creating a new client
    public ManagerDto managerTrigger(){

        Long randomId = 1L + new Random().nextLong(getAll().size());
        return mapper.mapToDto(repository.findById(randomId).get());
    }
}
