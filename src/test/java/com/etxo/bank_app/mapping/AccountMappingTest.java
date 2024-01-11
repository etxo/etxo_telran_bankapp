package com.etxo.bank_app.mapping;

import com.etxo.bank_app.dto.AccountDto;
import com.etxo.bank_app.dto.AccountDtoShort;
import com.etxo.bank_app.entity.Account;
import com.etxo.bank_app.entity.enums.AccountType;
import com.etxo.bank_app.entity.enums.Currency;
import com.etxo.bank_app.entity.enums.Status;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

class AccountMappingTest {

    Account expectedEntity;
    AccountDto expectedDto;
    AccountMapping mapping;
    @BeforeEach
    void setUp() {
        Faker faker = new Faker();

        expectedEntity = new Account();
        expectedEntity.setId(1L);
        expectedEntity.setIban(faker.finance().iban());
        expectedEntity.setBic(faker.finance().bic());
        expectedEntity.setAccountType(AccountType.DEBIT);
        expectedEntity.setStatus(Status.ACTIVE);
        expectedEntity.setBalance(new BigDecimal(0));
        expectedEntity.setCurrencyCode(Currency.EUR);
        expectedEntity.setCreatedAt(new Timestamp(19191919));
        expectedEntity.setUpdatedAt(new Timestamp(19191919));

        expectedDto = new AccountDto();
        expectedDto.setId(1L);
        expectedDto.setIban(expectedEntity.getIban());
        expectedDto.setBic(expectedEntity.getBic());
        expectedDto.setType(AccountType.DEBIT);
        expectedDto.setStatus(Status.ACTIVE);
        expectedDto.setBalance(expectedEntity.getBalance());
        expectedDto.setCurrencyCode(expectedEntity.getCurrencyCode());
        expectedDto.setCreatedAt(expectedEntity.getCreatedAt());
        expectedDto.setUpdatedAt(expectedEntity.getUpdatedAt());

        mapping = new AccountMapping();
    }

    @Test
    void itShouldMapToDto() {
        assertEquals(expectedDto, mapping.mapToDto(expectedEntity));
    }

    @Test
    void itShouldMapToEntity() {
        expectedEntity.setId(null);
        expectedEntity.setCreatedAt(null);
        expectedEntity.setUpdatedAt(null);

        assertEquals(expectedEntity, mapping.mapToEntity(expectedDto));
    }
}