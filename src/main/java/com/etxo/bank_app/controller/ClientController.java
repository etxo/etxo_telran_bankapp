package com.etxo.bank_app.controller;

import com.etxo.bank_app.dto.ClientDto;
import com.etxo.bank_app.entity.Address;
import com.etxo.bank_app.entity.Client;
import com.etxo.bank_app.service.AccountService;
import com.etxo.bank_app.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService service;

    @GetMapping
    public List<ClientDto> getClients(){

        return new ArrayList<>(service.getClients());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDto> getClientById(Long id){

        return ResponseEntity.ok(service.getClientById(id));
    }

    @PostMapping
    public ResponseEntity<ClientDto> create(@RequestBody ClientDto client){
        return ResponseEntity.ok(service.create(client));
    }

    @PutMapping("/{email}")
    public ResponseEntity<ClientDto> update(@PathVariable String email,
                                            @RequestBody Address address){

        return ResponseEntity.ok(service.updateAddress(email, address));
    }
}