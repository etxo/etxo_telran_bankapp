package com.etxo.bank_app.service;

import com.etxo.bank_app.dto.AccountDto;

import com.etxo.bank_app.entity.Account;
import com.etxo.bank_app.entity.Client;
import com.etxo.bank_app.entity.enums.AccountType;
import com.etxo.bank_app.entity.enums.Currency;
import com.etxo.bank_app.entity.enums.Status;
import com.etxo.bank_app.exceptions.ClientNotFoundException;
import com.etxo.bank_app.mapping.AccountMapping;
import com.etxo.bank_app.reposi.AccountRepository;
import com.etxo.bank_app.reposi.ClientRepository;
import com.github.javafaker.Faker;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
@RequiredArgsConstructor // DI over constructor!
public class AccountService {

    private final AccountRepository accountRepo;
    private final ClientRepository clientRepo;
    private final AccountMapping mapper;

    /*public Set<AccountDto> getAccountsByClientId(Long clientId){

        Set<Account> accounts = accountRepo.getAccountsByClientId(clientId);
        accounts.stream().forEach(System.out::println);// just to test
        return new HashSet<>(accounts.stream()
                .map(mapping::mapToDto).toList());
    }*/

    public List<AccountDto> getAccountsByClientId(Long clientId){
        return accountRepo.getAccountsByClientId(clientId).stream()
                .map(mapper::mapToDto).toList();
    }

    @Transactional
    public AccountDto create(AccountDto dto){

        Client client = null;
        if(dto.getClient().getId() != null) {
            client = clientRepo.findById(dto.getClient().getId()).orElse(null);
        }else if(dto.getClient().getEmail() != null){
            client = clientRepo.getClientByEmail(dto.getClient().getEmail()).orElse(null);
        }
        else throw new ClientNotFoundException("client not found!");

        Account account = mapper.mapToEntity(dto);
        account.setClient(client);
        Account savedAccount = accountRepo.save(account);
        return mapper.mapToDto(savedAccount);
    }

}
