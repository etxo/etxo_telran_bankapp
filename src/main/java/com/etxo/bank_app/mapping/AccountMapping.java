package com.etxo.bank_app.mapping;

import com.etxo.bank_app.dto.AccountDto;
import com.etxo.bank_app.dto.AccountDtoShort;
import com.etxo.bank_app.dto.ClientDto;
import com.etxo.bank_app.entity.Account;
import com.etxo.bank_app.entity.enums.AccountType;
import com.etxo.bank_app.entity.enums.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AccountMapping {

    public AccountDto mapToDto(Account entity){
        AccountDto dto = new AccountDto();
        dto.setId(entity.getId());
        dto.setIban(entity.getIban());
        dto.setBic(entity.getBic());
        dto.setType(entity.getAccountType());
        dto.setStatus(entity.getStatus());
        dto.setBalance(entity.getBalance());
        dto.setCurrencyCode(entity.getCurrencyCode());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());

        return dto;
    }

    public AccountDtoShort mapToDtoShort(Account entity){
        AccountDtoShort dto = new AccountDtoShort();
        dto.setId(entity.getId());
        dto.setIban(entity.getIban());
        dto.setBic(entity.getBic());
        dto.setBalance(entity.getBalance());
        return dto;
    }
    public Account mapToEntity(AccountDto dto){
        Account entity = new Account();
        entity.setIban(dto.getIban());
        entity.setBic(dto.getBic());
        entity.setAccountType(AccountType.DEBIT);
        entity.setStatus(Status.ACTIVE);
        entity.setBalance(new BigDecimal(0));
        entity.setCurrencyCode(dto.getCurrencyCode());

        return entity;
    }
}
