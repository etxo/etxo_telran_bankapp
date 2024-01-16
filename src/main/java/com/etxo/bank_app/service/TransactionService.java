package com.etxo.bank_app.service;

import com.etxo.bank_app.dto.TransactionDto;
import com.etxo.bank_app.entity.Account;
import com.etxo.bank_app.entity.enums.Status;
import com.etxo.bank_app.exceptions.AccountNotFoundException;
import com.etxo.bank_app.exceptions.ClientNotFoundException;
import com.etxo.bank_app.mapping.TransactionMapping;
import com.etxo.bank_app.reposi.AccountRepository;
import com.etxo.bank_app.reposi.TransactionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepo;
    private final AccountRepository accountRepo;
    private final TransactionMapping mapper;

    @Transactional
    public TransactionDto execute(TransactionDto dto) throws AccountNotFoundException {

        Long senderId = dto.getSender().getId();
        Long receiverId = dto.getReceiver().getId();

        if (!(accountRepo.existsById(senderId)
                    || accountRepo.findById(senderId).get().getStatus().equals(Status.ACTIVE))) {
            throw new AccountNotFoundException(
                    "you cannot execute a transaction, because your account is disabled!");
        }

        if (!(accountRepo.existsById(dto.getReceiver().getId())
                    || accountRepo.findById(receiverId).get().getStatus().equals(Status.ACTIVE))) {
            throw new AccountNotFoundException("this receiver account does not exist!");
        }

        //TODO Business logic for executing a transaction.
        Account sender = accountRepo.findById(senderId).get();
        Account receiver = accountRepo.findById(receiverId).get();
        TransactionDto savedDto = null;

        if (sender.getBalance().compareTo(dto.getAmount()) >= 0) {

            sender.setBalance(sender.getBalance().subtract(dto.getAmount()));
            receiver.setBalance(receiver.getBalance().add(dto.getAmount()));
            accountRepo.save(sender);
            accountRepo.save(receiver);
            savedDto = mapper.mapToDto(transactionRepo.save(mapper.mapToEntity(dto)));
        }

        return savedDto;
    }

    public List<TransactionDto> getTransactionsByClientId(Long clientId)
            throws ClientNotFoundException {
        return transactionRepo.findTransactionsByClientId(clientId).stream()
                .map(mapper::mapToDto).toList();
    }

    public List<TransactionDto> getTransactionsByAccountId(Long accountId)
            throws AccountNotFoundException {

        return transactionRepo.findTransactionsByAccountId(accountId).stream()
                .map(mapper::mapToDto).toList();
    }
}
