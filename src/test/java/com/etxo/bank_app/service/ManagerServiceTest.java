package com.etxo.bank_app.service;

import com.etxo.bank_app.dto.ManagerDto;
import com.etxo.bank_app.entity.Manager;
import com.etxo.bank_app.entity.enums.Status;
import com.etxo.bank_app.mapping.ManagerMapping;
import com.etxo.bank_app.reposi.ManagerRepository;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ManagerServiceTest {

    @Mock
    private ManagerRepository repository;
    @Mock
    private ManagerMapping mapper;
    @InjectMocks
    private ManagerService service;

    private Manager expectedManager;
    private ManagerDto expectedManagerDto;

    @BeforeEach
    void setUp() {

        service = new ManagerService(repository, mapper);
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
    void itShouldCreateManager() {
        when(repository.existsByEmail(anyString())).thenReturn(false);
        when(mapper.mapToEntity(any(ManagerDto.class)))
                .thenReturn(expectedManager);
        when(repository.save(any(Manager.class)))
                .thenReturn(expectedManager);
        when(mapper.mapToDto(any(Manager.class)))
                .thenReturn(expectedManagerDto);

        ManagerDto createdManagerDto = service.create(expectedManagerDto);
        assertEquals(expectedManagerDto, createdManagerDto);
    }
}