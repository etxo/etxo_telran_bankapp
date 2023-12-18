package com.etxo.bank_app.service;

import com.etxo.bank_app.dto.AddressDto;
import com.etxo.bank_app.dto.ClientDto;
import com.etxo.bank_app.dto.ManagerDto;
import com.etxo.bank_app.entity.Address;
import com.etxo.bank_app.entity.Client;
import com.etxo.bank_app.entity.Manager;
import com.etxo.bank_app.entity.enums.CountryCode;
import com.etxo.bank_app.entity.enums.Status;
import com.etxo.bank_app.mapping.AddressMapping;
import com.etxo.bank_app.mapping.ClientMapping;
import com.etxo.bank_app.reposi.ClientRepository;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClientServiceTest {

    @Mock
    private ClientRepository repository;
    @Mock
    private AddressMapping addressMapper;
   @Mock
    private ClientMapping clientMapper;
    @InjectMocks
    private ClientService clientService;
    @InjectMocks
    private ClientDto expectClientDto;
    @InjectMocks
    private Client expectClient;
    //private AddressDto addressDto;
    @InjectMocks
    private Address expectAddress;
    @InjectMocks
    private Manager manager;
    //private ManagerDto managerDto;
    @Test
    @Disabled
    void getClientsTest() {
    }

    @Test
    @Disabled
    void getClientByIdTest() {
    }

    @Test
    @Disabled
    void getClientByEmailTest() {
    }

    @BeforeEach
    void init(){
        Faker faker = new Faker();
        expectAddress.setPostalCode(faker.address().zipCode());
        expectAddress.setCity(faker.address().city());
        expectAddress.setStreet(faker.address().streetName());
        expectAddress.setHouseNr(faker.address().buildingNumber());
        expectAddress.setCountryCode(CountryCode.DE);
        expectClient.setAddress(expectAddress);

        manager.setId(1L);
        manager.setFirstName(faker.name().firstName());
        manager.setLastName(faker.name().lastName());
        manager.setEmail(faker.internet().emailAddress());
        manager.setPhone(faker.phoneNumber().phoneNumber());
        manager.setStatus(Status.ACTIVE);
        manager.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        manager.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        expectClient.setManager(manager);

        expectClient.setFirstName(faker.name().firstName());
        expectClient.setLastName(faker.name().lastName());
        expectClient.setEmail(faker.internet().emailAddress());
        expectClient.setPhone(faker.phoneNumber().phoneNumber().toString());
        expectClient.setStatus(Status.ACTIVE);
    }
    @Test
    void createClientTest() {

        when(addressMapper.mapToEntity(any(AddressDto.class))).thenReturn(expectAddress);
        when(clientMapper.mapToEntity(any(ClientDto.class))).thenReturn(expectClient);
        when(clientMapper.mapToDto(any(Client.class))).thenReturn(expectClientDto);
        when(repository.findById(anyLong())).thenReturn(Optional.of(expectClient));

        ClientDto expectClientDto = clientMapper.mapToDto(expectClient);
        expectClientDto.setId(null);

        ClientDto clientDto = clientService.create(expectClientDto);
        assertEquals(expectClientDto, clientDto);
    }
}