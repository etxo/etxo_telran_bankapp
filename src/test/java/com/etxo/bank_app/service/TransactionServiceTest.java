package com.etxo.bank_app.service;

import com.etxo.bank_app.dto.AccountDto;
import com.etxo.bank_app.dto.TransactionDto;
import com.etxo.bank_app.entity.Account;
import com.etxo.bank_app.entity.Client;
import com.etxo.bank_app.entity.Transaction;
import com.etxo.bank_app.entity.enums.AccountType;
import com.etxo.bank_app.entity.enums.Currency;
import com.etxo.bank_app.entity.enums.Status;
import com.etxo.bank_app.mapping.TransactionMapping;
import com.etxo.bank_app.reposi.AccountRepository;
import com.etxo.bank_app.reposi.TransactionRepository;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {

    @InjectMocks
    private TransactionService service;
    @Mock
    private TransactionRepository transactionRepo;
    @Mock
    private AccountRepository accountRepo;
    @Mock
    private TransactionMapping mapper;

    private Account givenSender;
    private Account givenReceiver;
    private Transaction expectedTransaction;
    private TransactionDto expectedTransactionDto;

    @BeforeEach
    void init() {

        Faker faker = new Faker(new Locale("DE"));

        givenSender = new Account();
        givenSender.setId(1L);
        givenSender.setClient(new Client());
        givenSender.setIban(faker.finance().iban());
        givenSender.setBic(faker.finance().bic());
        givenSender.setAccountType(AccountType.DEBIT);
        givenSender.setStatus(Status.ACTIVE);
        givenSender.setBalance(new BigDecimal("100"));
        givenSender.setCurrencyCode(Currency.EUR);

        AccountDto givenSenderDto = new AccountDto();
        givenSenderDto.setId(1L);
        givenSenderDto.setIban(givenSender.getIban());
        givenSenderDto.setBic(givenSender.getBic());
        givenSenderDto.setBalance(givenSender.getBalance());

        givenReceiver = new Account();
        givenReceiver.setId(2L);
        givenReceiver.setClient(new Client());
        givenReceiver.setIban(faker.finance().iban());
        givenReceiver.setBic(faker.finance().bic());
        givenReceiver.setAccountType(AccountType.DEBIT);
        givenReceiver.setStatus(Status.ACTIVE);
        givenReceiver.setBalance(new BigDecimal("100"));
        givenReceiver.setCurrencyCode(Currency.EUR);

        AccountDto givenReceiverDto = new AccountDto();
        givenReceiverDto.setId(2L);
        givenReceiverDto.setIban(givenReceiver.getIban());
        givenReceiverDto.setBic(givenReceiver.getBic());
        givenReceiverDto.setBalance(givenReceiver.getBalance());

        expectedTransaction = new Transaction();
        expectedTransaction.setId(1L);
        expectedTransaction.setSender(givenSender);
        expectedTransaction.setReceiver(givenReceiver);
        expectedTransaction.setAmount(new BigDecimal("25"));

        expectedTransactionDto = new TransactionDto();
        expectedTransactionDto.setId(1L);
        expectedTransactionDto.setSender(givenSenderDto);
        expectedTransactionDto.setReceiver(givenReceiverDto);
        expectedTransactionDto.setAmount(new BigDecimal("25"));

    }
    @Captor
    ArgumentCaptor<Account> accountArgCaptor;
    @Test
    //@Disabled
    void itShouldExecuteTransaction() {

        when(accountRepo.existsById(anyLong())).thenReturn(true);
        when(accountRepo.findById(1L)).thenReturn(Optional.of(givenSender));
        when(accountRepo.findById(2L)).thenReturn(Optional.of(givenReceiver));

        when(accountRepo.save(any(Account.class))).thenReturn(givenSender);
        when(mapper.mapToEntity(expectedTransactionDto)).thenReturn(expectedTransaction);
        when(transactionRepo.save(any(Transaction.class))).thenReturn(expectedTransaction);
        when(mapper.mapToDto(expectedTransaction)).thenReturn(expectedTransactionDto);

        TransactionDto savedDto = service.execute(expectedTransactionDto);
        verify(accountRepo, times(2)).save(accountArgCaptor.capture());
        List<Account> capturedAccounts = accountArgCaptor.getAllValues();

        assertEquals(expectedTransactionDto, savedDto);
        assertEquals(new BigDecimal("75"), capturedAccounts.get(0).getBalance());
        assertEquals(new BigDecimal("125"), capturedAccounts.get(1).getBalance());
    }

    @Test
    void itShouldGetTransactionsByClientId() {

        when(transactionRepo.findTransactionsByClientId(anyLong()))
                .thenReturn(List.of(expectedTransaction));
        when(mapper.mapToDto(any(Transaction.class))).thenReturn(expectedTransactionDto);

        assertIterableEquals(List.of(expectedTransactionDto),
                service.getTransactionsByClientId(1L));
    }
}