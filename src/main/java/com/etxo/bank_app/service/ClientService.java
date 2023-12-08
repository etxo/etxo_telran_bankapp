package com.etxo.bank_app.service;

import com.etxo.bank_app.dto.ClientDto;
import com.etxo.bank_app.entity.Address;
import com.etxo.bank_app.entity.Client;
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

        return new ArrayList<>(repository.findAll()
                .stream()
                .map(ClientMapping::mapToDto)
                .toList());
    }

    public ClientDto getClientById(Long id){
        Client client = repository.findById(id).orElse(null);
        return client == null ? null : mapping.mapToDto(client);
    }

    public ClientDto create(ClientDto client){
        if (repository.existsByEmail(client.getEmail())){
            throw new RuntimeException("A client with such an email already exists!");
        }
        ClientDto savedClient = mapping.mapToDto(
                repository.save(mapping.mapToEntityNew(client)));
        return savedClient;
    }

    public ClientDto updateAddress(String email, Address address){

        Client client = repository.getClientByEmail(email)
                .orElseThrow(() -> new RuntimeException("there is no client with such an email!"));

        ClientDto savedClient = mapping.mapToDto(client);
        savedClient.setAddress(address);
        repository.save(mapping.mapToEntityNew(savedClient));

        return savedClient;
    }
}
