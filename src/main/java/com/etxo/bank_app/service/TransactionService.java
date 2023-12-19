package com.etxo.bank_app.service;

import com.etxo.bank_app.dto.TransactionDto;
import com.etxo.bank_app.entity.Account;
import com.etxo.bank_app.exceptions.AccountNotFoundException;
import com.etxo.bank_app.exceptions.ClientNotFoundException;
import com.etxo.bank_app.mapping.TransactionMapping;
import com.etxo.bank_app.reposi.AccountRepository;
import com.etxo.bank_app.reposi.TransactionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    private final TransactionMapping mapper;

    @Transactional
    public TransactionDto execute(TransactionDto dto) throws AccountNotFoundException {

        if (!accountRepository.existsById(dto.getSender().getId()))
            throw new AccountNotFoundException("this sender account does not exist!");
        if (!accountRepository.existsById(dto.getReceiver().getId()))
            throw new AccountNotFoundException("this receiver account does not exist!");

        //TODO Business logic for executing a transaction.
        Account sender = accountRepository.findById(dto.getId()).get();
        Account receiver = accountRepository.findById(dto.getId()).get();
        TransactionDto savedDto = null;

        if (sender.getBalance().compareTo(dto.getAmount()) >= 0) {

            sender.setBalance(sender.getBalance().subtract(dto.getAmount()));
            receiver.setBalance(receiver.getBalance().add(dto.getAmount()));
            accountRepository.save(sender);
            accountRepository.save(receiver);
            savedDto = mapper.mapToDto(transactionRepository.save(mapper.mapToEntity(dto)));
        }

        return savedDto;
    }

    public List<TransactionDto> getTransactionsByClientId(Long clientId) throws ClientNotFoundException {
        return transactionRepository.findTransactionsByClientId(clientId).stream()
                .map(mapper::mapToDto).toList();
    }

    public List<TransactionDto> getTransactionsByAccountId(Long accountId) throws AccountNotFoundException {

        return transactionRepository.findTransactionsByAccountId(accountId).stream()
                .map(mapper::mapToDto).toList();
    }
}
