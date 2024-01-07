package com.etxo.bank_app.mapping;

import com.etxo.bank_app.dto.AccountDto;
import com.etxo.bank_app.dto.TransactionDto;
import com.etxo.bank_app.entity.Account;
import com.etxo.bank_app.entity.Transaction;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class TransactionMapping {

    public TransactionDto mapToDto(Transaction entity){

        TransactionDto dto = new TransactionDto();
        dto.setId(entity.getId());

        AccountDto sender = new AccountDto();
        sender.setId(entity.getSender().getId());
        sender.setIban(entity.getSender().getIban());
        dto.setSender(sender);

        AccountDto receiver = new AccountDto();
        receiver.setId(entity.getReceiver().getId());
        receiver.setIban(entity.getReceiver().getIban());
        dto.setReceiver(receiver);

        dto.setAmount(entity.getAmount());
        dto.setExecutedAt(entity.getExecutedAt());

        return dto;
    }

    public Transaction mapToEntity(TransactionDto dto){

        Transaction entity = new Transaction();

        Account sender = new Account();
        sender.setId(dto.getSender().getId());
        entity.setSender(sender);

        Account receiver = new Account();
        receiver.setId(dto.getReceiver().getId());
        entity.setReceiver(receiver);

        entity.setAmount(dto.getAmount());

        return entity;
    }
}
