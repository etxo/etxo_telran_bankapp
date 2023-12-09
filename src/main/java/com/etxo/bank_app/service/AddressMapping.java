package com.etxo.bank_app.service;

import com.etxo.bank_app.dto.AddressDto;
import com.etxo.bank_app.entity.Address;
import org.springframework.stereotype.Service;

@Service
public class AddressMapping {

    public static AddressDto mapToDto(Address entity){
        AddressDto dto = new AddressDto();
        dto.setId(entity.getId());
        dto.setPostalCode(entity.getPostalCode());
        dto.setCity(entity.getCity());
        dto.setStreet(entity.getStreet());
        dto.setHouseNr(entity.getHouseNr());
        dto.setCountryCode(entity.getCountryCode());
        return dto;
    }

    public static Address mapToEntity(AddressDto dto){
        Address entity = new Address();
        entity.setPostalCode(dto.getPostalCode());
        entity.setCity(dto.getCity());
        entity.setStreet(dto.getStreet());
        entity.setHouseNr(dto.getHouseNr());
        entity.setCountryCode(dto.getCountryCode());
        return entity;
    }
}
