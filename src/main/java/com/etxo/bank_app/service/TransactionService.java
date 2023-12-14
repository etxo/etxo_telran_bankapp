package com.etxo.bank_app.service;

import com.etxo.bank_app.dto.TransactionDto;
import com.etxo.bank_app.reposi.AccountRepository;
import com.etxo.bank_app.reposi.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
        public TransactionDto create(TransactionDto dto){
            return null;
        }

        public Set<TransactionDto> getByAccountId(Long id){
            return null;
        }

        
}
