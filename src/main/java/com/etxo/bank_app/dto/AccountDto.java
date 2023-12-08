package com.etxo.bank_app.dto;


import com.etxo.bank_app.entity.Client;
import com.etxo.bank_app.entity.enums.Status;
import com.etxo.bank_app.entity.enums.AccountType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Setter
@Getter
@NoArgsConstructor
public class AccountDto {
    private Long id;
    @JsonProperty("client")
    private ClientDto client;
    private String iban;
    private String bic;
    private AccountType type;
    private Status status;
    private BigDecimal balance;
    private String currencyCode;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
