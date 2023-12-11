package com.etxo.bank_app.controller;

import com.etxo.bank_app.dto.ClientDto;
import com.etxo.bank_app.dto.AccountDto;
import com.etxo.bank_app.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("api/client")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService service;

    @GetMapping
    public List<ClientDto> getClients(){

        return new ArrayList<>(service.getClients());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDto> getClientById(@PathVariable Long id){

        return ResponseEntity.ok(service.getClientById(id));
    }



    @PostMapping
    public ResponseEntity<ClientDto> create(@RequestBody ClientDto client){
        return ResponseEntity.ok(service.create(client));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientDto> update(@PathVariable Long id,
                                            @RequestBody ClientDto dto){

        return ResponseEntity.ok(service.updateById(id, dto));
    }

    @PutMapping("/{email}")
    public ResponseEntity<Set<AccountDto>> createAccountByEmail(@PathVariable String email,
                                                         @RequestBody AccountDto accountDto){
        return ResponseEntity.ok(service.addAccountByEmail(email, accountDto));
    }

    /*
    @PutMapping("/{id}")
    public ResponseEntity<Set<AccountDto>> createAccountById(@PathVariable Long id,
                                                         @RequestBody AccountDto accountDto){
        return ResponseEntity.ok(service.addAccountById(id, accountDto));
    }
     */

    @DeleteMapping("/{id}")
    public ResponseEntity<ClientDto> delete(@PathVariable Long id){

        return ResponseEntity.ok(service.delete(id));
    }
}
