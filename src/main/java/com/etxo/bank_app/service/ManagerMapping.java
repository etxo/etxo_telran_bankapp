package com.etxo.bank_app.service;

import com.etxo.bank_app.dto.ManagerDto;
import com.etxo.bank_app.entity.Manager;
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
        entity.setStatus(dto.getStatus());

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

        return dto;
    }
}
