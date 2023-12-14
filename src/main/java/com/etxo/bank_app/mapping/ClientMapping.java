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
        dto.setAddress(addressMapper.mapToDto(entity.getAddress()));
        dto.setPhone(entity.getPhone());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());
        dto.setManager(managerMapper.mapToDto(entity.getManager()));

        return dto;
    }
    public Client mapToEntity(ClientDto dto){
        Client client = new Client();
        client.setStatus(Status.ACTIVE);
        client.setFirstName(dto.getFirstName());
        client.setLastName(dto.getLastName());
        client.setEmail(dto.getEmail());
        client.setAddress(addressMapper.mapToEntity(dto.getAddress()));
        client.setPhone(dto.getPhone());
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
                addressMapper.mapToEntity(dto.getAddress()));
        if(dto.getPhone() != null) entity.setPhone(dto.getPhone());
        if(dto.getManager() != null) entity.setManager(
                managerMapper.mapToEntity(dto.getManager()));
        return entity;
    }
}
