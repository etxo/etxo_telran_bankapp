package com.etxo.bank_app.service;

import com.etxo.bank_app.dto.ClientDto;
import com.etxo.bank_app.dto.ClientDtoUpdate;
import com.etxo.bank_app.entity.Client;
import com.etxo.bank_app.entity.Manager;
import com.etxo.bank_app.entity.enums.Status;
import com.etxo.bank_app.exceptions.ClientNotFoundException;
import com.etxo.bank_app.exceptions.ManagerNotFoundException;
import com.etxo.bank_app.mapping.AddressMapping;
import com.etxo.bank_app.mapping.ClientMapping;
import com.etxo.bank_app.mapping.ManagerMapping;
import com.etxo.bank_app.reposi.ClientRepository;
import com.etxo.bank_app.reposi.ManagerRepository;
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
    private final ManagerRepository managerRepo;
    public Set<ClientDto> getClients(){

        return new HashSet<>(repository.findAll()
                .stream()
                .map(clientMapper::mapToDto)
                .toList());
    }

    public ClientDto getClientById(Long id){
        Client client = repository.findById(id).orElseThrow(
                () -> new ClientNotFoundException(
                        String.format("NO CLIENT WITH ID: %s", id)));

        ClientDto dto = clientMapper.mapToDto(client);

        return dto;
    }

    public ClientDto getClientByEmail(String email){
        Client client = repository.getClientByEmail(email)
                .orElseThrow(() -> new ClientNotFoundException(
                        String.format("There is no client with email: %s", email)));
        return clientMapper.mapToDto(client);
    }

    public ClientDto create(ClientDto clientDto){
        if (repository.existsByEmail(clientDto.getEmail())){
            throw new RuntimeException(String.format(
                    "A client with email %s already exists!", clientDto.getEmail()));
        }

        ClientDto savedClientDto = clientMapper.mapToDto(
                repository.save(clientMapper.mapToEntity(clientDto)));
        return savedClientDto;
    }

    // this service allows to update
    public ClientDto updateById(Long id, ClientDtoUpdate dto){

        Client client = repository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException(
                        String.format("There is no client with id: %s", id)));

        if(dto.getLastName() != null) client.setLastName(dto.getLastName());
        if(dto.getEmail() != null) client.setEmail(dto.getEmail());
        if(dto.getAddress() != null) client.setAddress(
                addressMapper.mapToEntity(dto.getAddress()));
        if(dto.getPhone() != null) client.setPhone(dto.getPhone());

        if(dto.getManagerId() != null){
            Manager manager = managerRepo.findById(dto.getManagerId())
                    .orElseThrow(() -> new ManagerNotFoundException(
                            String.format("There is no manager with id: %s", dto.getManagerId())));
            client.setManager(manager);
        }
        Client updatedClient = repository.save(client);
        ClientDto updatedClientDto = clientMapper.mapToDto(updatedClient);
        return updatedClientDto;
    }

    public ClientDto delete(Long id) throws ClientNotFoundException {
        Client client = repository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException(
                        String.format("There is no client with id: %s", id)));
        client.setStatus(Status.INACTIVE);
        Client savedClient = repository.save(client);
        ClientDto savedClientDto = clientMapper.mapToDto(savedClient);
        return savedClientDto;
    }
}
