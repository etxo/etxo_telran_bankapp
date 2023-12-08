package com.etxo.bank_app.service;

import com.etxo.bank_app.dto.ClientDto;
import com.etxo.bank_app.entity.Client;
import com.etxo.bank_app.reposi.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository repository;
    private final ClientMapping mapping;
    public List<ClientDto> getClients(){

        return new ArrayList<>(repository.findAll()
                .stream()
                .map(ClientMapping::mapToDto)
                .toList());
    }

    public ClientDto getClientById(Long id){
        Optional<Client> client = repository.findById(id);
        if(client == null){
            return null;
        }
        return mapping.mapToDto(client.get());
    }

    public ClientDto save(ClientDto client){
        ClientDto savedClient = mapping.mapToDto(
                repository.save(mapping.mapToEntity(client)));
        return savedClient;
    }
}
