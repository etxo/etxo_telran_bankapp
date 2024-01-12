package com.etxo.bank_app.mapping;

import com.etxo.bank_app.dto.AccountDtoShort;
import com.etxo.bank_app.dto.AddressDto;
import com.etxo.bank_app.dto.ManagerDto;
import com.etxo.bank_app.entity.enums.Status;
import com.etxo.bank_app.service.ManagerService;
import com.etxo.bank_app.utility.RandomData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.etxo.bank_app.dto.ClientDto;
import com.etxo.bank_app.entity.*;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClientMappingTest {

    @Mock
    private ManagerService managerService;
    @Mock
    private ManagerMapping managerMapper;
    @Mock
    private AddressMapping addressMapper;
    @Mock
    private AccountMapping accountMapper;

    @InjectMocks
    private ClientMapping clientMapper;
    private Client expectedEntity;
    private AccountDtoShort accountDto;
    private AddressDto addressDto;
    private ManagerDto managerDto;
    private ClientDto expectedDto;
    private RandomData data;

    @BeforeEach
    void init() {

        data = new RandomData();
        Manager manager = new Manager();
        manager.setId(1L);
        expectedEntity = data.generateRandomClient(manager);

        expectedEntity.setAccounts(
                List.of(data.generateAccountForClient(expectedEntity)));

        addressDto = mock(AddressDto.class);
        accountDto = mock(AccountDtoShort.class);

        expectedDto = new ClientDto();
        expectedDto.setId(expectedEntity.getId());
        expectedDto.setStatus(Status.ACTIVE);
        expectedDto.setFirstName(expectedEntity.getFirstName());
        expectedDto.setLastName(expectedEntity.getLastName());
        expectedDto.setEmail(expectedEntity.getEmail());
        expectedDto.setAddress(addressDto);
        expectedDto.setPhone(expectedEntity.getPhone());
        expectedDto.setCreatedAt(new Timestamp(123));
        expectedDto.setUpdatedAt(new Timestamp(123));

        managerDto = new ManagerDto();
        managerDto.setId(1L);
        expectedDto.setManager(managerDto);
        expectedDto.setAccounts(List.of(accountDto));
    }

    @Test
    @Disabled
    void itShouldMapToDto() {

        clientMapper = new ClientMapping(managerService,
                managerMapper, addressMapper, accountMapper);

        when(addressMapper.mapToDto(any(Address.class)))
                .thenReturn(addressDto);
        when(managerMapper.mapToDto(any(Manager.class)))
                .thenReturn(managerDto);
        when(accountMapper.mapToDtoShort(any(Account.class)))
                .thenReturn(accountDto);

        assertIterableEquals(expectedDto.getAccounts(),
                clientMapper.mapToDto(expectedEntity).getAccounts());

        ClientDto actualDto = clientMapper.mapToDto(expectedEntity);

        assertEquals(expectedDto.getId(), actualDto.getId());
        assertEquals(expectedDto.getStatus(), actualDto.getStatus());
        assertEquals(expectedDto.getFirstName(), actualDto.getFirstName());
        assertEquals(expectedDto.getLastName(), actualDto.getLastName());
        assertEquals(expectedDto.getEmail(), actualDto.getEmail());
        assertEquals(expectedDto.getPhone(), actualDto.getPhone());
    }

    @Test
    void itShouldMapToEntity() {
    }
}