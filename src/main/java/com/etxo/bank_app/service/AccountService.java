package com.etxo.bank_app.service;

import com.etxo.bank_app.dto.AccountDto;
import com.etxo.bank_app.entity.*;
import com.etxo.bank_app.entity.enums.AccountType;
import com.etxo.bank_app.entity.enums.Status;
import com.etxo.bank_app.reposi.AccountRepository;
import com.etxo.bank_app.reposi.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor // DI over constructor!
public class AccountService {

    private final AccountRepository repository;
    private final ClientRepository clientRepo;

    public Account create(AccountDto dto){

        Client client = clientRepo.findById(dto.getClientId()).orElse(null);
        if(client == null){

            client = new Client();
            clientRepo.save(client);

        }
        Account account = new Account();
        account.setClient(client);
        account.setIban(dto.getIban());
        account.setBic(dto.getBic());
        account.setAccountType(AccountType.DEBIT);
        account.setStatus(Status.ACTIVE);
        account.setBalance(new BigDecimal(0));
        account.setCurrencyCode(dto.getCurrencyCode());
        account.setCreatedAt(new Timestamp(System.currentTimeMillis()));

        return repository.save(account);
    }

    public List<Account> getAll(){
        return repository.findAll();
    }
}
