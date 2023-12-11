package com.etxo.bank_app.service;

import com.etxo.bank_app.dto.AccountDto;
import com.etxo.bank_app.dto.AddressDto;
import com.etxo.bank_app.dto.ClientDto;
import com.etxo.bank_app.entity.Account;
import com.etxo.bank_app.entity.Client;
import com.etxo.bank_app.entity.enums.Status;
import com.etxo.bank_app.reposi.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository repository;
    private final ClientMapping mapping;
    private final AccountMapping accountMapping;
    public Set<ClientDto> getClients(){

        return new HashSet<>(repository.findAll()
                .stream()
                .map(mapping::mapToDto)
                .toList());
    }

    public ClientDto getClientById(Long id){
        Client client = repository.findById(id).orElse(null);
        return client == null ? null : mapping.mapToDto(client);
    }

    public ClientDto getClientByEmail(String email){
        Client client = repository.getClientByEmail(email)
                .orElseThrow(() -> new RuntimeException("There is no client with such an email!"));
        return mapping.mapToDto(client);
    }

    public ClientDto create(ClientDto client){
        if (repository.existsByEmail(client.getEmail())){
            throw new RuntimeException("A client with such an email already exists!");
        }
        ClientDto savedClient = mapping.mapToDto(
                repository.save(mapping.mapToEntity(client)));
        return savedClient;
    }

    public ClientDto updateById(Long id, ClientDto dto){

        Client client = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("there is no client with this id!"));

        return mapping.mapToDto(repository.save(ClientMapping.mapToEntityUpdate(client, dto)));
    }

    public Set<AccountDto> addAccountByEmail(String email, AccountDto accountDto){

        Client client = repository.getClientByEmail(email)
                .orElseThrow(() -> new RuntimeException("There is no client with such an email!"));
        Set<Account> accounts = client.getAccounts();
        accountDto.setClient(mapping.mapToDto(client));
        accounts.add(accountMapping.mapToEntity(accountDto));
        client.setAccounts(accounts);

        return new HashSet<>(client.getAccounts().stream()
                .map(accountMapping::mapToDto).toList());
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
