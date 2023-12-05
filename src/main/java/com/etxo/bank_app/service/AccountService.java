package com.etxo.bank_app.service;

import com.etxo.bank_app.dto.AccountDto;
import com.etxo.bank_app.entity.Account;
import com.etxo.bank_app.reposi.AccountRepository;
import com.etxo.bank_app.reposi.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor // DI over constructor!
public class AccountService {

    private final AccountRepository repository;
    private final ClientRepository clientRepo;

    public Account create(AccountDto dto){

        if(clientRepo.findById(dto.getClient().getId()) != null){

        }
        return null;
    }
}
