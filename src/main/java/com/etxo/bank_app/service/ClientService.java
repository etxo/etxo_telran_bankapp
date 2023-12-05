package com.etxo.bank_app.service;

import com.etxo.bank_app.dto.ClientDto;
import com.etxo.bank_app.reposi.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository repository;
    private final ClientMapping mapping;
    public List<ClientDto> getClients(){
        List<ClientDto> clients = new ArrayList<>(repository.findAll()
                .stream()
                .map(ClientMapping::mapToDto)
                .toList());
        repository.findAll()
                .stream()
                .map(ClientMapping::mapToDto)
                .toList();
        return new ArrayList<>();
    }
}
