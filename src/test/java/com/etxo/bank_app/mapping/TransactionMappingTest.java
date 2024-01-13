package com.etxo.bank_app.mapping;

import com.etxo.bank_app.dto.AccountDto;
import com.etxo.bank_app.dto.TransactionDto;
import com.etxo.bank_app.entity.Account;
import com.etxo.bank_app.entity.Transaction;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

class TransactionMappingTest {
    private Transaction expectedEntity;
    private TransactionDto expectedDto;
    private TransactionMapping mapper;


    @BeforeEach
    void setUp() {

        Faker faker = new Faker();
        Account expectedSender = new Account();
        expectedSender.setId(1L);
        expectedSender.setIban(faker.finance().iban());
        expectedSender.setBic(faker.finance().bic());

        AccountDto expectedSenderDto = new AccountDto();
        expectedSenderDto.setId(1L);
        expectedSenderDto.setIban(expectedSender.getIban());
        expectedSenderDto.setBic(expectedSender.getBic());

        Account expectedReceiver = new Account();
        expectedReceiver.setId(2L);
        expectedReceiver.setIban(faker.finance().iban());
        expectedReceiver.setBic(faker.finance().bic());

        AccountDto expectedReceiverDto = new AccountDto();
        expectedReceiverDto.setId(2L);
        expectedReceiverDto.setIban(expectedReceiver.getIban());
        expectedReceiverDto.setBic(expectedReceiver.getBic());

        expectedEntity = new Transaction();
        expectedEntity.setId(1L);
        expectedEntity.setSender(expectedSender);
        expectedEntity.setReceiver(expectedReceiver);
        expectedEntity.setAmount(new BigDecimal("125.00"));

        expectedDto = new TransactionDto();
        expectedDto.setId(1L);
        expectedDto.setSender(expectedSenderDto);
        expectedDto.setReceiver(expectedReceiverDto);
        expectedDto.setAmount(expectedEntity.getAmount());

        mapper = new TransactionMapping();
    }

    @Test
    void mapToDto() {

        assertThat(Objects.equals(expectedDto,
                mapper.mapToDto(expectedEntity)));
    }

    @Test
    void mapToEntity() {

        assertThat(Objects.equals(expectedDto,
                mapper.mapToDto(expectedEntity)));
    }
}