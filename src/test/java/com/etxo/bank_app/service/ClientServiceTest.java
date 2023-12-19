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

    private Address expectedAddress;
    private Client expectedClient;
    private Manager manager;
    private ClientDto expectedClientDto;


    @BeforeEach
    void init(){
        Faker faker = new Faker();
        expectedAddress = new Address();
        expectedAddress.setPostalCode(faker.address().zipCode());
        expectedAddress.setCity(faker.address().city());
        expectedAddress.setStreet(faker.address().streetName());
        expectedAddress.setHouseNr(faker.address().buildingNumber());
        expectedAddress.setCountryCode(CountryCode.DE);
        expectedClient = new Client();
        expectedClient.setAddress(expectedAddress);

        manager = new Manager();
        manager.setId(1L);
        manager.setFirstName(faker.name().firstName());
        manager.setLastName(faker.name().lastName());
        manager.setEmail(faker.internet().emailAddress());
        manager.setPhone(faker.phoneNumber().phoneNumber());
        manager.setStatus(Status.ACTIVE);
        manager.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        manager.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        expectedClient.setManager(manager);

        expectedClient.setFirstName(faker.name().firstName());
        expectedClient.setLastName(faker.name().lastName());
        expectedClient.setEmail(faker.internet().emailAddress());
        expectedClient.setPhone(faker.phoneNumber().phoneNumber().toString());
        expectedClient.setStatus(Status.ACTIVE);

        expectedClientDto = new ClientDto();
        expectedClientDto.setStatus(Status.ACTIVE);
        expectedClientDto.setFirstName(expectedClient.getFirstName());
        expectedClientDto.setLastName(expectedClient.getLastName());
        expectedClientDto.setEmail(expectedClient.getEmail());
        expectedClientDto.setPhone(expectedClient.getPhone());
    }
    @Test
    void createClientTest() {

        when(addressMapper.mapToEntity(any(AddressDto.class))).thenReturn(expectedAddress);
        when(clientMapper.mapToEntity(any(ClientDto.class))).thenReturn(expectedClient);
        when(clientMapper.mapToDto(any(Client.class))).thenReturn(expectedClientDto);
        when(repository.findById(anyLong())).thenReturn(Optional.of(expectedClient));

        ClientDto clientDto = clientMapper.mapToDto(expectedClient);
        //expectClientDto.setId(null);

        ClientDto savedClientDto = clientService.create(clientDto);
        assertEquals(expectedClientDto.getEmail(), savedClientDto.getEmail());
    }
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
}