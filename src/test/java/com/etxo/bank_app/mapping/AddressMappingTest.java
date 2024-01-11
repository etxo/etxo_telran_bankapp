package com.etxo.bank_app.mapping;

import com.etxo.bank_app.dto.AddressDto;
import com.etxo.bank_app.entity.Address;
import com.etxo.bank_app.entity.enums.CountryCode;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class AddressMappingTest {

    AddressMapping mapper;
    Address expectedEntity;
    AddressDto expectedDto;

    @BeforeEach
    void setUp() {

        Faker faker = new Faker(new Locale("FR"));
        expectedEntity = new Address();
        expectedEntity.setId(1L);
        expectedEntity.setPostalCode(faker.address().zipCode());
        expectedEntity.setCity(faker.address().city());
        expectedEntity.setStreet(faker.address().streetName());
        expectedEntity.setHouseNr(faker.address().buildingNumber());
        expectedEntity.setCountryCode(CountryCode.FR);

        expectedDto = new AddressDto();
        expectedDto.setId(expectedEntity.getId());
        expectedDto.setPostalCode(expectedEntity.getPostalCode());
        expectedDto.setCity(expectedEntity.getCity());
        expectedDto.setStreet(expectedEntity.getStreet());
        expectedDto.setHouseNr(expectedEntity.getHouseNr());
        expectedDto.setCountryCode(expectedEntity.getCountryCode());

        mapper = new AddressMapping();
    }

    @Test
    void itShouldMapToDto() {
        assertEquals(expectedDto, mapper.mapToDto(expectedEntity));
    }

    @Test
    void itShouldMapToEntity() {
        expectedEntity.setId(null);
        assertEquals(expectedEntity, mapper.mapToEntity(expectedDto));
    }
}