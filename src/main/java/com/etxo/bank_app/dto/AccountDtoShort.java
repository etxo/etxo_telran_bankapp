package com.etxo.bank_app.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter @Setter
@NoArgsConstructor
@EqualsAndHashCode
public class AccountDtoShort {

    private Long Id;
    private String iban;
    private String bic;
    private BigDecimal balance;
}
