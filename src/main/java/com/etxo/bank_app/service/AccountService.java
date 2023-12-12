package com.etxo.bank_app.service;

import com.etxo.bank_app.dto.AccountDto;

import com.etxo.bank_app.reposi.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor // DI over constructor!
public class AccountService {

    private final AccountRepository repository;
    private final AccountMapping mapping;

    public Set<AccountDto> getAll(){

        return new HashSet<>(repository.findAll().stream()
                .map(mapping::mapToDto)
                .toList());
    }
}
