package com.etxo.bank_app.service;

import com.etxo.bank_app.mapping.AccountMapping;
import com.etxo.bank_app.mapping.AddressMapping;
import com.etxo.bank_app.mapping.ClientMapping;
import com.etxo.bank_app.reposi.ClientRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ClientServiceTest {

    @Mock
    private ClientRepository repository;
    @Mock
    private AddressMapping addressMapper;
   @Mock
    private ClientMapping clientMapper;

    @Test
    void getClientsTest() {
    }

    @Test
    void getClientByIdTest() {
    }

    @Test
    void getClientByEmailTest() {
    }

    @Test
    void createTest() {
    }
}