package com.etxo.bank_app.service;

import com.etxo.bank_app.dto.AccountDto;

import com.etxo.bank_app.entity.Account;
import com.etxo.bank_app.entity.Client;
import com.etxo.bank_app.exceptions.ClientNotFoundException;
import com.etxo.bank_app.mapping.AccountMapping;
import com.etxo.bank_app.reposi.AccountRepository;
import com.etxo.bank_app.reposi.ClientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor // DI over constructor!
public class AccountService {

    private final AccountRepository accountRepo;
    private final ClientRepository clientRepo;
    private final AccountMapping mapper;

    public List<AccountDto> getAccountsByClientId(Long clientId)
            throws ClientNotFoundException{
        if(clientRepo.existsById(clientId)) {
            return accountRepo.getAccountsByClientId(clientId).stream()
                    .map(mapper::mapToDto).toList();
        }else {
            throw new ClientNotFoundException(
                    String.format("NO CLIENT with ID: %s", clientId));
        }
    }

    @Transactional
    public AccountDto create(AccountDto dto) throws ClientNotFoundException{

        Client client = clientRepo.getClientByEmail(dto.getClient().getEmail())
                .orElseThrow(() -> new ClientNotFoundException("NO CLIENT FOUND!"));

        Account account = mapper.mapToEntity(dto);
        account.setClient(client);
        return mapper.mapToDto(accountRepo.save(account));
    }
}
