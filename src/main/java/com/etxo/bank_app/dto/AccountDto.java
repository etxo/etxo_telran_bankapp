package com.etxo.bank_app.dto;


import com.etxo.bank_app.entity.Status;
import com.etxo.bank_app.entity.AccountType;
import com.etxo.bank_app.entity.Agreement;
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
    private Long clientId;
    private String iban;
    private String bic;
    private AccountType type;
    private Status status;
    private BigDecimal balance;
    private String currencyCode;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private List<Agreement> agreements;
}
