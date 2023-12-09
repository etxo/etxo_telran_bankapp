package com.etxo.bank_app.service;

import com.etxo.bank_app.dto.ManagerDto;
import com.etxo.bank_app.entity.Manager;
import com.etxo.bank_app.entity.enums.Status;
import org.springframework.stereotype.Service;

@Service
public class ManagerMapping {

    public static Manager mapToEntity(ManagerDto dto){
        Manager entity = new Manager();
        entity.setId(dto.getId());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setEmail(dto.getEmail());
        //manager.setAddress(dto.getAddress());
        entity.setPhone(dto.getPhone());
        entity.setStatus(
                dto.getStatus() == null ? Status.ACTIVE : dto.getStatus());

        return entity;
    }

    public static ManagerDto mapToDto(Manager entity){
        ManagerDto dto = new ManagerDto();
        dto.setId(entity.getId());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setEmail(entity.getEmail());
        dto.setStatus(entity.getStatus());
        //dto.setAddress(entity.getAddress());
        dto.setPhone(entity.getPhone());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());

        return dto;
    }

    public static Manager mapToEntityUpdate(Manager entity, ManagerDto dto){
        //entity.setId(dto.getId());
        if(dto.getFirstName() != null) entity.setFirstName(dto.getFirstName());
        if(dto.getLastName() != null) entity.setLastName(dto.getLastName());
        if(dto.getEmail() != null) entity.setEmail(dto.getEmail());
        if(dto.getStatus() != null) entity.setStatus(dto.getStatus());
        if(dto.getPhone() != null) entity.setPhone(dto.getPhone());
        //manager.setAddress(dto.getAddress());

        return entity;
    }
}
