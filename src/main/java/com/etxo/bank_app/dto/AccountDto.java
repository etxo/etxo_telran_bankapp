package com.etxo.bank_app.dto;


import com.etxo.bank_app.entity.enums.Currency;
import com.etxo.bank_app.entity.enums.Status;
import com.etxo.bank_app.entity.enums.AccountType;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
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

    @NotNull
    @JsonProperty("client")
    private ClientDto client;

    @Pattern(regexp = "[A-Z]{2}[0-9]{2}[a-zA-Z0-9]{1,30}")
    private String iban;

    @Pattern(regexp = "[A-Z]{6,9}")
    private String bic;

    private AccountType type;
    private Status status;

    private BigDecimal balance;

    //@NotEmpty(message = "set currency, please")
    private Currency currencyCode;

    private Timestamp createdAt;
    private Timestamp updatedAt;
}
