package com.etxo.bank_app.dto;

import com.etxo.bank_app.entity.Account;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class TransactionDto {

    private Long id;

    @JsonProperty
    @NotNull
    private AccountDto sender;

    @JsonProperty
    @NotNull
    private AccountDto receiver;

    @Min(1)
    private BigDecimal amount;

    private Timestamp executedAt;
}
