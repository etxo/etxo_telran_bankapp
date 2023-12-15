package com.etxo.bank_app.controller;

import com.etxo.bank_app.dto.TransactionDto;
import com.etxo.bank_app.exceptions.AccountNotFoundException;
import com.etxo.bank_app.exceptions.ClientNotFoundException;
import com.etxo.bank_app.service.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/transaction")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService service;
    @PostMapping
    public ResponseEntity<TransactionDto> create(
            @Valid @RequestBody TransactionDto dto) throws AccountNotFoundException {
        return ResponseEntity.ok(service.create(dto));
    }

    @GetMapping("/client_id/{id}")
    public ResponseEntity<List<TransactionDto>> getAllByClientId(
            @PathVariable Long id) throws ClientNotFoundException {
        return ResponseEntity.ok(service.getAllByClientId(id));
    }
}
