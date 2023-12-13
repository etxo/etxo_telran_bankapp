package com.etxo.bank_app.mapping;

import com.etxo.bank_app.dto.AccountDto;
import com.etxo.bank_app.entity.Account;
import com.etxo.bank_app.entity.enums.AccountType;
import com.etxo.bank_app.entity.enums.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class AccountMapping {

    private final ClientMapping clientMapping;
    public AccountDto mapToDto(Account entity){
        AccountDto dto = new AccountDto();
        dto.setId(entity.getId());
        dto.setIban(entity.getIban());
        dto.setType(entity.getAccountType());
        dto.setStatus(entity.getStatus());
        dto.setBalance(entity.getBalance());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());
        dto.setClient(clientMapping.mapToDto(entity.getClient()));

        //dto.setAccounts(entity.getAccounts().stream().map());

        return dto;
    }
    public Account mapToEntity(AccountDto dto){
        Account entity = new Account();
        entity.setClient(clientMapping.mapToEntity(dto.getClient()));
        entity.setIban(dto.getIban());
        entity.setBic(dto.getBic());
        entity.setAccountType(AccountType.DEBIT);
        entity.setStatus(Status.ACTIVE);
        entity.setBalance(new BigDecimal(0));
        entity.setCurrencyCode(dto.getCurrencyCode());

        return entity;
    }
}