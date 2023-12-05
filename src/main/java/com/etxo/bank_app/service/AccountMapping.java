package com.etxo.bank_app.service;

import com.etxo.bank_app.dto.AccountDto;
import com.etxo.bank_app.dto.ClientDto;
import com.etxo.bank_app.entity.Account;
import com.etxo.bank_app.entity.Client;

public class AccountMapping {
    public static AccountDto mapToDto(Account entity){
        AccountDto dto = new AccountDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setType(entity.getType());
        dto.setStatus(entity.getStatus());
        dto.setBalance(entity.getBalance());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());
        dto.setClientId(entity.getClient().getId());

        //dto.setAccounts(entity.getAccounts().stream().map());

        return dto;
    }
}
