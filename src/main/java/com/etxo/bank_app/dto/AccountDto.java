package com.etxo.bank_app.dto;


import com.etxo.bank_app.entity.Status;
import com.etxo.bank_app.entity.AccountType;
import com.etxo.bank_app.entity.Agreement;
import com.etxo.bank_app.entity.Client;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class AccountDto {
    private Long id;
    private String name;
    private AccountType type;
    private Status status;
    private BigDecimal balance;
    private String currencyCode;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private Long clientId;
    private List<Agreement> agreement;
}
