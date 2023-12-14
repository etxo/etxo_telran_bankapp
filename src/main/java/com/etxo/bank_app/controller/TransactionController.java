package com.etxo.bank_app.controller;

import com.etxo.bank_app.dto.TransactionDto;
import com.etxo.bank_app.exceptions.AccountNotFoundException;
import com.etxo.bank_app.service.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService service;
    @PostMapping
    public ResponseEntity<TransactionDto> create(@Valid TransactionDto dto) throws AccountNotFoundException {

        return ResponseEntity.ok(service.create(dto));
    }

}
