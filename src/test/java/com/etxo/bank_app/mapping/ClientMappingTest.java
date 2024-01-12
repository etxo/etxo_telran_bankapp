package com.etxo.bank_app.mapping;

import com.etxo.bank_app.service.ManagerService;
import com.etxo.bank_app.utility.RandomData;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import com.etxo.bank_app.dto.ClientDto;
import com.etxo.bank_app.entity.*;

import static org.junit.jupiter.api.Assertions.*;

class ClientMappingTest {

    @Mock
    private ManagerService managerService;
    @Mock
    private ManagerMapping managerMapper;
    @Mock
    private AddressMapping addressMapper;
    @Mock
    private AccountMapping accountMapper;

    private ClientMapping clientMapper;
    private Client expectedEntity;
    private ClientDto expectedDto;
    private RandomData data;

    @BeforeEach
    void setUp() {

        clientMapper = new ClientMapping(managerService,
                managerMapper, addressMapper, accountMapper);

        Faker faker = new Faker();


    }

    @Test
    void itShouldMapToDto() {

    }

    @Test
    void itShouldMapToEntity() {
    }
}