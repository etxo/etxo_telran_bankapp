package com.etxo.bank_app.reposi;

import com.etxo.bank_app.entity.Transaction;
import com.etxo.bank_app.utility.RandomData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class TransactionRepositoryTest {

    @Autowired
    private TransactionRepository repository;

    @Test
    void findTransactionsByAccountId() {
    }

    @Test
    void findTransactionsByClientId() {
    }
}