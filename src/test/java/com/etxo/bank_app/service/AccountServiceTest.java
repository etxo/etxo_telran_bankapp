package com.etxo.bank_app.service;

import com.etxo.bank_app.dto.AccountDto;
import com.etxo.bank_app.dto.ClientDto;
import com.etxo.bank_app.entity.Account;
import com.etxo.bank_app.entity.Client;
import com.etxo.bank_app.mapping.AccountMapping;
import com.etxo.bank_app.reposi.AccountRepository;
import com.etxo.bank_app.reposi.ClientRepository;
import com.etxo.bank_app.utility.RandomData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    @Mock
    private AccountRepository accountRepo;
    @Mock
    private ClientRepository clientRepo;
    @Mock
    private AccountMapping mapper;
    @InjectMocks
    private AccountService service;
    @Captor
    ArgumentCaptor<Account> accountArgCaptor;
    private Client givenClient;
    private Account givenAccount;
    private AccountDto dto;

    @BeforeEach
    void setUp() {

        RandomData data = new RandomData();
        givenClient = data.generateRandomClient(
                data.generateRandomManager());
        givenAccount = data.generateAccountForClient(givenClient);
        dto = new AccountDto();
        ClientDto givenClientDto = new ClientDto();
        givenClientDto.setEmail(givenClient.getEmail());
        dto.setClient(givenClientDto);
        dto.setIban(givenAccount.getIban());
        dto.setBic(givenAccount.getBic());
        dto.setType(givenAccount.getAccountType());
        dto.setStatus(givenAccount.getStatus());
        dto.setBalance(givenAccount.getBalance());
        dto.setCurrencyCode(givenAccount.getCurrencyCode());
    }

    @Test
    void itShouldCreateAccount() {

        when(clientRepo.getClientByEmail(anyString()))
                .thenReturn(Optional.of(givenClient));
        when(mapper.mapToEntity(any(AccountDto.class)))
                .thenReturn(givenAccount);

        service.create(dto);
        verify(accountRepo).save(accountArgCaptor.capture());
        assertEquals(givenAccount, accountArgCaptor.getValue());
    }
}