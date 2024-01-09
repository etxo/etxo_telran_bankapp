package com.etxo.bank_app.reposi;

import com.etxo.bank_app.entity.Account;
import com.etxo.bank_app.entity.Client;
import com.etxo.bank_app.entity.Transaction;
import com.etxo.bank_app.entity.enums.AccountType;
import com.etxo.bank_app.entity.enums.Currency;
import com.etxo.bank_app.entity.enums.Status;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class TransactionRepositoryTest {

    @Autowired
    private TransactionRepository repository;

    private Transaction expTransaction;
    @BeforeEach
    void init(){

        Faker faker = new Faker();

        Client expClient = new Client();
        expClient.setId(1L);

        Account expSender = new Account();
        expSender.setClient(expClient);
        expSender.setId(1L);
        expSender.setIban(faker.finance().iban());
        expSender.setAccountType(AccountType.DEBIT);
        expSender.setStatus(Status.ACTIVE);
        expSender.setBalance(BigDecimal.valueOf(100));
        expSender.setCurrencyCode(Currency.EUR);

        Account expReceiver = new Account();
        expReceiver.setId(2L);
        expReceiver.setIban(faker.finance().iban());
        expReceiver.setAccountType(AccountType.DEBIT);
        expReceiver.setStatus(Status.ACTIVE);
        expReceiver.setBalance(BigDecimal.valueOf(100));
        expReceiver.setCurrencyCode(Currency.EUR);

        expTransaction = new Transaction();
        expTransaction.setAmount(BigDecimal.valueOf(25));
        expTransaction.setSender(expSender);
        expTransaction.setReceiver(expReceiver);

    }


    @Test
    void shouldFindTransactionsByAccountId() {

        repository.save(expTransaction);
        Transaction savedTransaction = repository.findTransactionsByAccountId(1L).get(0);
        assertEquals(expTransaction, savedTransaction);
    }

    @Test
    void shouldFindTransactionsByClientId() {

        repository.save(expTransaction);
        Transaction savedTransaction = repository.findTransactionsByClientId(1L).get(0);
        assertEquals(expTransaction, savedTransaction);
    }
}