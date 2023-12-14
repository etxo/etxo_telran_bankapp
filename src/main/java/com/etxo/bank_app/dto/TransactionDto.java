package com.etxo.bank_app.dto;

import com.etxo.bank_app.entity.Account;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter@Setter
@NoArgsConstructor
public class TransactionDto {
    private Long id;
    @JsonProperty
    private AccountDto sender;

    @JsonProperty
    private Account receiver;

    private BigDecimal amount;

    private Timestamp executedAt;
}
