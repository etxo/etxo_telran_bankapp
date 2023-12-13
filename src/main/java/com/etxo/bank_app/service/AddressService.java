package com.etxo.bank_app.service;

import com.etxo.bank_app.dto.AddressDto;
import com.etxo.bank_app.mapping.AddressMapping;
import com.etxo.bank_app.reposi.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressService {
    private final AddressRepository repository;
    private final AddressMapping mapper;

    public AddressDto save(AddressDto dto){

        return mapper.mapToDto(repository.save(mapper.mapToEntity(dto)));
    }
}
