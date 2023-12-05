package com.etxo.bank_app.controller;

import com.etxo.bank_app.dto.ClientDto;
import com.etxo.bank_app.entity.Client;
import com.etxo.bank_app.service.AccountService;
import com.etxo.bank_app.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
