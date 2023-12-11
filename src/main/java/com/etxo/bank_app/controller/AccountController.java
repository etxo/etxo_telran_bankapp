package com.etxo.bank_app.controller;

import com.etxo.bank_app.dto.AccountDto;
import com.etxo.bank_app.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/account")
public class AccountController {

    private final AccountService service;
    @GetMapping
    public ResponseEntity<Set<AccountDto>> getAll(){

        return ResponseEntity.ok(service.getAll());
    }
}
