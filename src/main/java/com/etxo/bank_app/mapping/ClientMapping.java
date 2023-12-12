package com.etxo.bank_app.mapping;

import com.etxo.bank_app.dto.ClientDto;
import com.etxo.bank_app.entity.*;
import com.etxo.bank_app.entity.enums.Status;
import com.etxo.bank_app.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
@RequiredArgsConstructor
public class ClientMapping {

    private final ManagerService managerService;
    private final ManagerMapping managerMapper;
    private final AddressMapping addressMapper;
    public ClientDto mapToDto(Client entity){
        ClientDto dto = new ClientDto();
        dto.setId(entity.getId());
        dto.setStatus(entity.getStatus());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setEmail(entity.getEmail());
        dto.setAddress(AddressMapping.mapToDto(entity.getAddress()));
        dto.setPhone(entity.getPhone());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());
        dto.setManager(managerMapper.mapToDto(entity.getManager()));

        //dto.setAccounts(entity.getAccounts().stream().map());

        return dto;
    }
    public Client mapToEntity(ClientDto dto){
        Client client = new Client();
        client.setStatus(Status.ACTIVE);
        if(dto.getFirstName().isEmpty()) throw new RuntimeException();
        client.setFirstName(dto.getFirstName());
        if(dto.getLastName().isEmpty()) throw new RuntimeException();
        client.setLastName(dto.getLastName());
        if(dto.getEmail().isEmpty()) throw new RuntimeException();
        client.setEmail(dto.getEmail());
        if(dto.getAddress() == null) throw new RuntimeException();
        client.setAddress(AddressMapping.mapToEntity(dto.getAddress()));
        if(dto.getPhone().isEmpty()) throw new RuntimeException();
        client.setPhone(dto.getPhone());
        //System.out.println();
        client.setManager(managerMapper.mapToEntity(
                managerService.managerTrigger()));
        client.setAccounts(new HashSet<>());

        return client;
    }

    public Client mapToEntityUpdate(Client entity, ClientDto dto){
        if(dto.getFirstName() != null) entity.setFirstName(dto.getFirstName());
        if(dto.getLastName() != null) entity.setLastName(dto.getLastName());
        if(dto.getEmail() != null) entity.setEmail(dto.getEmail());
        if(dto.getAddress() != null) entity.setAddress(
                AddressMapping.mapToEntity(dto.getAddress()));
        if(dto.getPhone() != null) entity.setPhone(dto.getPhone());
        if(dto.getManager() != null) entity.setManager(
                managerMapper.mapToEntity(dto.getManager()));
        return entity;
    }
}
