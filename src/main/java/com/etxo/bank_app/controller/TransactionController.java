package com.etxo.bank_app.controller;

import com.etxo.bank_app.dto.TransactionDto;
import com.etxo.bank_app.exceptions.AccountNotFoundException;
import com.etxo.bank_app.exceptions.ClientNotFoundException;
import com.etxo.bank_app.exceptions.NotOwnAccountException;
import com.etxo.bank_app.security.service.UserService;
import com.etxo.bank_app.service.ClientService;
import com.etxo.bank_app.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

    @Operation(summary = "executes transaction by sending account id" +
                            "and receiving account id.",
            description = "client should exist in the database")
    @ApiResponse(responseCode = "404",
            description = "account not found")
    @ApiResponse(responseCode = "403",
            description = "client is not the owner")
    @PostMapping
    @Secured("USER")
    public ResponseEntity<TransactionDto> execute(
            @Valid @RequestBody TransactionDto dto)
                    throws AccountNotFoundException,
                                NotOwnAccountException {

        if(userService.isOwner(clientService.getClientByAccountId(dto
                .getSender().getId()).getEmail())){
            return ResponseEntity.ok(service.execute(dto));
        }
        else throw new NotOwnAccountException(
                        "YOU ARE NOT ALLOWED TO USE THIS ACCOUNT!");
    }

    @Operation(summary = "gets transactions by client_id.",
            description = "client should exist in the database.")
    @ApiResponse(responseCode = "404",
            description = "client not found")
    @GetMapping("/client_id/{id}")
    @Secured("MANAGER")
    public ResponseEntity<List<TransactionDto>> getTransactionsByClientId(
            @PathVariable Long id) throws ClientNotFoundException {
        return ResponseEntity.ok(service.getTransactionsByClientId(id));
    }

    @Operation(summary = "gets transactions by account_id.",
            description = "account should exist in the database.")
    @ApiResponse(responseCode = "404",
            description = "account not found")
    @GetMapping("/account_id/{id}")
    @Secured("MANAGER")
    public ResponseEntity<List<TransactionDto>> getTransactionsByAccountId(
            @PathVariable Long id) throws AccountNotFoundException {
        return ResponseEntity.ok(service.getTransactionsByAccountId(id));
    }
}
