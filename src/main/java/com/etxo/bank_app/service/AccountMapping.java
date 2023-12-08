package com.etxo.bank_app.service;

import com.etxo.bank_app.dto.AccountDto;
import com.etxo.bank_app.entity.Account;
import com.etxo.bank_app.entity.enums.AccountType;
import com.etxo.bank_app.entity.enums.Status;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Service
public class AccountMapping {
    public static AccountDto mapToDto(Account entity){
        AccountDto dto = new AccountDto();
        dto.setId(entity.getId());
        dto.setIban(entity.getIban());
        dto.setType(entity.getAccountType());
        dto.setStatus(entity.getStatus());
        dto.setBalance(entity.getBalance());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());
        dto.setClient(ClientMapping.mapToDto(entity.getClient()));

        //dto.setAccounts(entity.getAccounts().stream().map());

        return dto;
    }
    public static Account mapToEntity(AccountDto dto){
        Account entity = new Account();
        entity.setClient(ClientMapping.mapToEntityNew(dto.getClient()));
        entity.setIban(dto.getIban());
        entity.setBic(dto.getBic());
        entity.setAccountType(AccountType.DEBIT);
        entity.setStatus(Status.ACTIVE);
        entity.setBalance(new BigDecimal(0));
        entity.setCurrencyCode(dto.getCurrencyCode());
        entity.setCreatedAt(new Timestamp(System.currentTimeMillis()));

        return entity;
    }
}
