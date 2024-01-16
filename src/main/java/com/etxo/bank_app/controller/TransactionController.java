package com.etxo.bank_app.controller;

import com.etxo.bank_app.dto.TransactionDto;
import com.etxo.bank_app.exceptions.AccountNotFoundException;
import com.etxo.bank_app.exceptions.ClientNotFoundException;
import com.etxo.bank_app.security.service.UserService;
import com.etxo.bank_app.service.ClientService;
import com.etxo.bank_app.service.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/transaction")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService service;
    private final ClientService clientService;
    private final UserService userService;

    @PostMapping
    @Secured("USER")
    public ResponseEntity<TransactionDto> execute(
            @Valid @RequestBody TransactionDto dto) throws AccountNotFoundException {

        if(userService.isOwner(clientService.getClientByAccountId(dto.getSender().getId()).getEmail())){
            return ResponseEntity.ok(service.execute(dto));
        }
        else return ResponseEntity.badRequest().body(null);
    }

    @GetMapping("/client_id/{id}")
    @Secured("MANAGER")
    public ResponseEntity<List<TransactionDto>> getTransactionsByClientId(
            @PathVariable Long id) throws ClientNotFoundException {
        return ResponseEntity.ok(service.getTransactionsByClientId(id));
    }

    @GetMapping("/account_id/{id}")
    @Secured("MANAGER")
    public ResponseEntity<List<TransactionDto>> getTransactionsByAccountId(
            @PathVariable Long id) throws AccountNotFoundException {
        return ResponseEntity.ok(service.getTransactionsByAccountId(id));
    }
}
