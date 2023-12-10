package com.etxo.bank_app.service;

import com.etxo.bank_app.dto.AddressDto;
import com.etxo.bank_app.dto.ClientDto;
import com.etxo.bank_app.entity.Client;
import com.etxo.bank_app.entity.enums.Status;
import com.etxo.bank_app.reposi.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository repository;
    //private final AddressRepository addrRepository;
    private final ClientMapping mapping;
    public List<ClientDto> getClients(){

        return new ArrayList<>(repository.findAll()
                .stream()
                .map(mapping::mapToDto)
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

    public ClientDto updateById(Long id, ClientDto dto){

        Client client = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("there is no client with this id!"));

        return mapping.mapToDto(repository.save(ClientMapping.mapToEntityUpdate(client, dto)));
    }

    public ClientDto updateAddressByClientId(Long id, AddressDto dto){
        Client client = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("there is no client with this id!"));
        client.setAddress(AddressMapping.mapToEntity(dto));
        return mapping.mapToDto(repository.save(client));
    }

    public ClientDto delete(Long id){
        Client client = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("there is no client with this id!"));
        client.setStatus(Status.INACTIVE);
        client = repository.save(client);
        return mapping.mapToDto(client);
    }
}
