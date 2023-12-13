package com.etxo.bank_app.service;

import com.etxo.bank_app.dto.AccountDto;

import com.etxo.bank_app.dto.ClientDto;
import com.etxo.bank_app.entity.Account;
import com.etxo.bank_app.entity.Client;
import com.etxo.bank_app.exceptions.ClientNotFoundException;
import com.etxo.bank_app.mapping.AccountMapping;
import com.etxo.bank_app.reposi.AccountRepository;
import com.etxo.bank_app.reposi.ClientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor // DI over constructor!
public class AccountService {

    private final AccountRepository accountRepo;
    private final ClientRepository clientRepo;
    private final AccountMapping mapping;

    public Set<AccountDto> getAccountsByClientId(Long clientId){
        return new HashSet<>(
                            accountRepo.getAccountsByClientId(clientId).stream()
                                .map(mapping::mapToDto).toList());
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

        Account account = mapping.mapToEntity(dto);
        account.setClient(client);
        return mapping.mapToDto(accountRepo.save(account));
    }
}
