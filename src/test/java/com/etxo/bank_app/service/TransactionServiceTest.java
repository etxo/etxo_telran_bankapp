package com.etxo.bank_app.service;

import com.etxo.bank_app.dto.AccountDto;
import com.etxo.bank_app.dto.AccountDtoShort;
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
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Locale;
import java.util.Optional;

import static com.etxo.bank_app.entity.enums.CountryCode.DE;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {

    @InjectMocks
    private TransactionService transactionService;
    @Mock
    private TransactionRepository transactionRepository;
    @Mock
    private AccountRepository accountRepository;
    @Mock
    private TransactionMapping mapper;

    private Account expectedSender;
    private Account expectedReceiver;
    private Transaction expectedTransaction;
    private TransactionDto expectedTransactionDto;

    @BeforeEach
    void init() {

        Faker faker = new Faker(new Locale("DE"));

        expectedSender = new Account();
        expectedSender.setId(1L);
        expectedSender.setClient(new Client());
        expectedSender.setIban(faker.finance().iban());
        expectedSender.setBic(faker.finance().bic());
        expectedSender.setAccountType(AccountType.DEBIT);
        expectedSender.setStatus(Status.ACTIVE);
        expectedSender.setBalance(new BigDecimal(100));
        expectedSender.setCurrencyCode(Currency.EUR);

        AccountDto expectedSenderDto = new AccountDto();
        expectedSenderDto.setId(1L);
        expectedSenderDto.setIban(expectedSender.getIban());
        expectedSenderDto.setBic(expectedSender.getBic());
        expectedSenderDto.setBalance(expectedSender.getBalance());

        expectedReceiver = new Account();
        expectedReceiver.setId(2L);
        expectedReceiver.setClient(new Client());
        expectedReceiver.setIban(faker.finance().iban());
        expectedReceiver.setBic(faker.finance().bic());
        expectedReceiver.setAccountType(AccountType.DEBIT);
        expectedReceiver.setStatus(Status.ACTIVE);
        expectedReceiver.setBalance(new BigDecimal(100));
        expectedReceiver.setCurrencyCode(Currency.EUR);

        AccountDto expectedReceiverDto = new AccountDto();
        expectedReceiverDto.setId(2L);
        expectedReceiverDto.setIban(expectedReceiver.getIban());
        expectedReceiverDto.setBic(expectedReceiver.getBic());
        expectedReceiverDto.setBalance(expectedReceiver.getBalance());

        expectedTransaction = new Transaction();
        expectedTransaction.setId(1L);
        expectedTransaction.setSender(expectedSender);
        expectedTransaction.setReceiver(expectedReceiver);
        expectedTransaction.setAmount(new BigDecimal(50));

        expectedTransactionDto = new TransactionDto();
        expectedTransactionDto.setId(1L);
        expectedTransactionDto.setSender(expectedSenderDto);
        expectedTransactionDto.setReceiver(expectedReceiverDto);
        expectedTransactionDto.setAmount(new BigDecimal(50));

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @Disabled
    void executeTest() {
        when(accountRepository.existsById(anyLong())).thenReturn(true);
        when(accountRepository.findById(1L)).thenReturn(Optional.of(expectedSender));
        when(accountRepository.findById(2L)).thenReturn(Optional.of(expectedReceiver));
        when(mapper.mapToDto(expectedTransaction)).thenReturn(expectedTransactionDto);
        when(mapper.mapToEntity(expectedTransactionDto)).thenReturn(expectedTransaction);

        TransactionDto savedDto = transactionService.execute(expectedTransactionDto);
        assertEquals(expectedTransactionDto, savedDto);
    }

    @Test
    void getTransactionsByClientIdTest() {
    }
}