package com.etxo.bank_app.controller;

import com.etxo.bank_app.dto.AccountDto;
import com.etxo.bank_app.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/account")
public class AccountController {

    private final AccountService service;
    private Long clientId;

    @GetMapping("/by_client/{clientId}")
    public ResponseEntity<List<AccountDto>> getAccountsByClientId(
            @PathVariable Long clientId){

        return ResponseEntity.ok(service.getAccountsByClientId(clientId));
    }

    @PostMapping
    public ResponseEntity<AccountDto> create(@RequestBody @Valid AccountDto dto){

        return ResponseEntity.ok(service.create(dto));
    }

}
