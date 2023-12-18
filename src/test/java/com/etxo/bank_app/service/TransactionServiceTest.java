package com.etxo.bank_app.service;

import com.etxo.bank_app.entity.Transaction;
import com.etxo.bank_app.mapping.TransactionMapping;
import com.etxo.bank_app.reposi.AccountRepository;
import com.etxo.bank_app.reposi.TransactionRepository;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

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

    @BeforeEach
    void init() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void executeTest() {
        // Given
    }

    @Test
    void getTransactionsByClientIdTest() {
    }
}