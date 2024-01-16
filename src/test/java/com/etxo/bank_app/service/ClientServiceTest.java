package com.etxo.bank_app.service;

import com.etxo.bank_app.dto.AddressDto;
import com.etxo.bank_app.dto.ClientDto;
import com.etxo.bank_app.dto.ClientDtoUpdate;
import com.etxo.bank_app.entity.Address;
import com.etxo.bank_app.entity.Client;
import com.etxo.bank_app.entity.Manager;
import com.etxo.bank_app.entity.enums.CountryCode;
import com.etxo.bank_app.entity.enums.Status;
import com.etxo.bank_app.mapping.AddressMapping;
import com.etxo.bank_app.mapping.ClientMapping;
import com.etxo.bank_app.reposi.ClientRepository;
import com.etxo.bank_app.utility.RandomData;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClientServiceTest {

    @Mock
    private ClientRepository repository;
    @Mock
    private ClientMapping clientMapper;
    @Mock
    private AddressMapping addressMapper;
    @InjectMocks
    private ClientService clientService;
    private Client expectedClient;
    private Client expectedClientWithId;
    private ClientDto expectedClientDto;
    private RandomData data;


    @BeforeEach
    void init(){
        Faker faker = new Faker();
        Address expectedAddress = new Address();
        expectedAddress.setPostalCode(faker.address().zipCode());
        expectedAddress.setCity(faker.address().city());
        expectedAddress.setStreet(faker.address().streetName());
        expectedAddress.setHouseNr(faker.address().buildingNumber());
        expectedAddress.setCountryCode(CountryCode.DE);
        expectedClient = new Client();
        expectedClient.setAddress(expectedAddress);

        Manager manager = new Manager();
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
        expectedClient.setPhone(faker.phoneNumber().phoneNumber());
        expectedClient.setStatus(Status.ACTIVE);

        expectedClientWithId = new Client();
        expectedClientWithId.setId(1L);
        expectedClientWithId.setFirstName(expectedClient.getFirstName());
        expectedClientWithId.setLastName(expectedClient.getLastName());
        expectedClientWithId.setEmail(expectedClient.getEmail());
        expectedClientWithId.setPhone(expectedClient.getPhone());
        expectedClientWithId.setStatus(expectedClient.getStatus());
        expectedClientWithId.setAddress(expectedClient.getAddress());
        expectedClientWithId.setManager(expectedClient.getManager());

        expectedClientDto = new ClientDto();
        expectedClientDto.setStatus(expectedClientWithId.getStatus());
        expectedClientDto.setFirstName(expectedClientWithId.getFirstName());
        expectedClientDto.setLastName(expectedClient.getLastName());
        expectedClientDto.setEmail(expectedClientWithId.getEmail());
        expectedClientDto.setPhone(expectedClientWithId.getPhone());
    }
    @Test
    void itShouldCreateClient() {

        when(clientMapper.mapToEntity(any(ClientDto.class)))
                .thenReturn(expectedClient);
        when(clientMapper.mapToDto(any(Client.class)))
                .thenReturn(expectedClientDto);
        when(repository.existsByEmail(anyString()))
                .thenReturn(false);
        when(repository.save(any(Client.class)))
                .thenReturn(expectedClientWithId);

        ClientDto savedClientDto = clientService.create(expectedClientDto);
        assertEquals(expectedClientDto, savedClientDto);
    }

    @Test
    void itShouldGetAllClients() {
        when(repository.findAll())
                .thenReturn(List.of(mock(Client.class)));
        when(clientMapper.mapToDto(any(Client.class)))
                .thenReturn(mock(ClientDto.class));

        assertFalse(clientService.getClients().isEmpty());
    }

    @Test
    void itShouldGetClientById() {
        when(repository.findById(anyLong()))
                .thenReturn(Optional.of(expectedClientWithId));
        when(clientMapper.mapToDto(any(Client.class)))
                .thenReturn(expectedClientDto);
        ClientDto clientDto = clientService.getClientById(1L);
        assertEquals(expectedClientDto, clientDto);
    }

    @Test
    //@Disabled
    void itShouldGetClientByEmail() {

        when(repository.getClientByEmail(anyString()))
                .thenReturn(Optional.of(expectedClient));
        when(clientMapper.mapToDto(any(Client.class)))
                .thenReturn(expectedClientDto);

        ClientDto foundClient = clientService.getClientByEmail(expectedClient.getEmail());
        assertEquals(expectedClientDto, foundClient);
    }

    @Test
    void itShouldUpdateClientById() {

        Faker faker = new Faker();
        Address newAddress = new Address();
        newAddress.setPostalCode(faker.address().zipCode());
        newAddress.setCity(faker.address().city());
        newAddress.setStreet(faker.address().streetName());
        newAddress.setHouseNr(faker.address().buildingNumber());
        newAddress.setCountryCode(CountryCode.DE);

        AddressDto newAddressDto = new AddressDto();
        newAddressDto.setPostalCode(newAddress.getPostalCode());
        newAddressDto.setCity(newAddress.getPostalCode());
        newAddressDto.setStreet(newAddress.getStreet());
        newAddressDto.setHouseNr(newAddress.getHouseNr());
        newAddressDto.setCountryCode(newAddress.getCountryCode());

        ClientDtoUpdate dtoUpdate = new ClientDtoUpdate();
        dtoUpdate.setAddress(newAddressDto);
        Client updatedClient = new Client();
        updatedClient.setAddress(newAddress);
        ClientDto updatedClientDto = new ClientDto();
        updatedClientDto.setAddress(newAddressDto);

        when(repository.findById(anyLong()))
                .thenReturn(Optional.of(expectedClientWithId));
        when(addressMapper.mapToEntity(any(AddressDto.class)))
                .thenReturn(newAddress);
        when(repository.save(any(Client.class)))
                .thenReturn(updatedClient);
        when(clientMapper.mapToDto(any(Client.class)))
                .thenReturn(updatedClientDto);

        assertEquals(updatedClientDto, clientService.updateById(
                1l, dtoUpdate));
    }

    @Captor
    private ArgumentCaptor<Client> clientCaptor;
    @Test
    void itShouldDeleteClientById(){

        data = new RandomData();
        expectedClient.setAccounts(List.of(
                data.generateAccountForClient(expectedClient)));

        when(repository.findById(anyLong()))
                .thenReturn(Optional.of(expectedClient));
        //ArgumentCaptor???
        clientService.deleteById(1L);
        verify(repository).save(clientCaptor.capture());
        Client savedClient = clientCaptor.getValue();

        assertEquals(Status.INACTIVE, savedClient.getStatus());
        assertEquals(Status.INACTIVE,
                savedClient.getAccounts().get(0).getStatus());
    }
}