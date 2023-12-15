package com.etxo.bank_app.service;

import com.etxo.bank_app.dto.TransactionDto;
import com.etxo.bank_app.exceptions.AccountNotFoundException;
import com.etxo.bank_app.exceptions.ClientNotFoundException;
import com.etxo.bank_app.mapping.TransactionMapping;
import com.etxo.bank_app.reposi.AccountRepository;
import com.etxo.bank_app.reposi.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    private final TransactionMapping mapper;
        public TransactionDto create(TransactionDto dto) throws AccountNotFoundException {

            if(!accountRepository.existsById(dto.getSender().getId()))
                throw new AccountNotFoundException("this sender account does not exist!");
            if(!accountRepository.existsById(dto.getReceiver().getId()))
                throw new AccountNotFoundException("this receiver account does not exist!");

            return mapper.mapToDto(transactionRepository.save(mapper.mapToEntity(dto)));
        }

        public List<TransactionDto> getAllByClientId(Long clientId) throws ClientNotFoundException {
            return transactionRepository.findTransactionsByClientId(clientId).stream()
                    .map(mapper::mapToDto).toList();
        }
        public List<TransactionDto> getAllByAccountId(Long accountId) throws AccountNotFoundException {

            return transactionRepository.findTransactionsByAccountId(accountId).stream()
                    .map(mapper::mapToDto).toList();
        }

        
}
