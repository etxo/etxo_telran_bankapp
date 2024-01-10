package com.etxo.bank_app.mapping;

import com.etxo.bank_app.dto.ManagerDto;
import com.etxo.bank_app.entity.Manager;
import com.etxo.bank_app.entity.enums.Status;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ManagerMappingTest {

    private ManagerMapping mapping;
    private Manager expectedManager;
    private ManagerDto expectedManagerDto;
    @BeforeEach
    void setUp() {

        mapping = new ManagerMapping();
        Faker faker = new Faker();
        expectedManager = new Manager();
        expectedManager.setId(1L);
        expectedManager.setFirstName(faker.name().firstName());
        expectedManager.setLastName(faker.name().lastName());
        expectedManager.setEmail(faker.internet().emailAddress());
        expectedManager.setPhone(faker.phoneNumber().phoneNumber());
        expectedManager.setStatus(Status.ACTIVE);

        expectedManagerDto = new ManagerDto();
        expectedManagerDto.setId(1L);
        expectedManagerDto.setFirstName(expectedManager.getFirstName());
        expectedManagerDto.setLastName(expectedManager.getLastName());
        expectedManagerDto.setEmail(expectedManager.getEmail());
        expectedManagerDto.setPhone(expectedManager.getPhone());
        expectedManagerDto.setStatus(Status.ACTIVE);

    }

    @Test
    void itShouldMapToEntity() {

        Manager mappedManager = mapping.mapToEntity(expectedManagerDto);
        assertEquals(expectedManager, mappedManager);
    }

    @Test
    void itShouldMapToDto() {

        ManagerDto mappedManagerDto = mapping.mapToDto(expectedManager);
        assertEquals(expectedManagerDto, mappedManagerDto);
    }

    @Test
    void itShouldMapToEntityUpdate() {

        String updatedEmail = "updated@mail.me";
        String updatedPhone = "+49307897878";
        ManagerDto updateDto = new ManagerDto();
        updateDto.setEmail(updatedEmail);
        updateDto.setPhone(updatedPhone);
        expectedManager.setEmail(updatedEmail);
        expectedManager.setPhone(updatedPhone);

        Manager updatedManager = mapping.mapToEntityUpdate(expectedManager, updateDto);
        assertEquals(expectedManager, updatedManager);
    }
}