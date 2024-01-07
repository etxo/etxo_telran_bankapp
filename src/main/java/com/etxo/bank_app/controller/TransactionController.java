package com.etxo.bank_app.controller;

import com.etxo.bank_app.dto.TransactionDto;
import com.etxo.bank_app.exceptions.AccountNotFoundException;
import com.etxo.bank_app.exceptions.ClientNotFoundException;
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
    @PostMapping
    @Secured("MANAGER")
    public ResponseEntity<TransactionDto> execute(
            @Valid @RequestBody TransactionDto dto) throws AccountNotFoundException {
        return ResponseEntity.ok(service.execute(dto));
    }

    @GetMapping("/client_id/{id}")
    public ResponseEntity<List<TransactionDto>> getTransactionsByClientId(
            @PathVariable Long id) throws ClientNotFoundException {
        return ResponseEntity.ok(service.getTransactionsByClientId(id));
    }
}
