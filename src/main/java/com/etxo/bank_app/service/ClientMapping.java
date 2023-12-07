package com.etxo.bank_app.service;

import com.etxo.bank_app.dto.ClientDto;
import com.etxo.bank_app.entity.*;
import org.springframework.stereotype.Service;

@Service
public class ClientMapping {

    public static ClientDto mapToDto(Client entity){
        ClientDto dto = new ClientDto();
        dto.setId(entity.getId());
        dto.setStatus(entity.getStatus());
        dto.setTaxCode(entity.getTaxCode());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setEmail(entity.getEmail());
        dto.setPhone(entity.getPhone());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());
        dto.setManager(entity.getManager());

        //dto.setAccounts(entity.getAccounts().stream().map());

        return dto;
    }
    public static Client mapToEntity(ClientDto dto){
        return null;
    }
}
