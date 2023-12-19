package com.etxo.bank_app.service;

import com.etxo.bank_app.dto.AccountDto;
import com.etxo.bank_app.dto.AddressDto;
import com.etxo.bank_app.dto.ClientDto;
import com.etxo.bank_app.entity.Account;
import com.etxo.bank_app.entity.Client;
import com.etxo.bank_app.entity.enums.Status;
import com.etxo.bank_app.exceptions.ClientNotFoundException;
import com.etxo.bank_app.mapping.AccountMapping;
import com.etxo.bank_app.mapping.AddressMapping;
import com.etxo.bank_app.mapping.ClientMapping;
import com.etxo.bank_app.reposi.AddressRepository;
import com.etxo.bank_app.reposi.ClientRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository repository;
    private final AddressMapping addressMapper;
    private final ClientMapping clientMapper;
    public Set<ClientDto> getClients(){

        return new HashSet<>(repository.findAll()
                .stream()
                .map(clientMapper::mapToDto)
                .toList());
    }

    public ClientDto getClientById(Long id){
        Client client = repository.findById(id).orElse(null);
        return client == null ? null : clientMapper.mapToDto(client);
    }

    public ClientDto getClientByEmail(String email){
        Client client = repository.getClientByEmail(email)
                .orElseThrow(() -> new ClientNotFoundException("There is no client with such an email!"));
        return clientMapper.mapToDto(client);
    }

    public ClientDto create(ClientDto clientDto){
        if (repository.existsByEmail(clientDto.getEmail())){
            throw new RuntimeException("A client with such an email already exists!");
        }

        ClientDto savedClientDto = clientMapper.mapToDto(
                repository.save(clientMapper.mapToEntity(clientDto)));
        return savedClientDto;
    }

    public ClientDto updateById(Long id, ClientDto dto){

        Client client = repository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException("there is no client with this id!"));

        return clientMapper.mapToDto(repository.save(clientMapper.mapToEntityUpdate(client, dto)));
    }

    public ClientDto delete(Long id){
        Client client = repository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException("there is no client with this id!"));
        client.setStatus(Status.INACTIVE);
        Client savedClient = repository.save(client);
        ClientDto savedClientDto = clientMapper.mapToDto(savedClient);
        return savedClientDto;
    }
}
