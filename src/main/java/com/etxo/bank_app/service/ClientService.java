package com.etxo.bank_app.service;

import com.etxo.bank_app.dto.ClientDto;
import com.etxo.bank_app.dto.ClientDtoUpdate;
import com.etxo.bank_app.entity.Account;
import com.etxo.bank_app.entity.Client;
import com.etxo.bank_app.entity.Manager;
import com.etxo.bank_app.entity.enums.Status;
import com.etxo.bank_app.exceptions.AccountNotFoundException;
import com.etxo.bank_app.exceptions.ClientNotFoundException;
import com.etxo.bank_app.exceptions.ManagerNotFoundException;
import com.etxo.bank_app.mapping.AddressMapping;
import com.etxo.bank_app.mapping.ClientMapping;
import com.etxo.bank_app.reposi.AccountRepository;
import com.etxo.bank_app.reposi.ClientRepository;
import com.etxo.bank_app.reposi.ManagerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepo;
    private final AddressMapping addressMapper;
    private final ClientMapping clientMapper;
    private final ManagerRepository managerRepo;
    private final AccountRepository accountRepo;
    public Set<ClientDto> getClients(){

        return new HashSet<>(clientRepo.findAll()
                .stream()
                .map(clientMapper::mapToDto)
                .toList());
    }

    public ClientDto getClientById(Long id) throws ClientNotFoundException{

        Client client = clientRepo.findById(id).orElseThrow(
                () -> new ClientNotFoundException(
                        String.format("NO CLIENT WITH ID: %s", id)));

        return clientMapper.mapToDto(client);
    }

    public ClientDto getClientByEmail(String email) throws ClientNotFoundException{

        Client client = clientRepo.getClientByEmail(email)
                .orElseThrow(() -> new ClientNotFoundException(
                        String.format("There is no client with email: %s", email)));
        return clientMapper.mapToDto(client);
    }

    public ClientDto getClientByAccountId(Long id) throws AccountNotFoundException{

        if(!accountRepo.existsById(id)){
            throw new AccountNotFoundException(
                    String.format("there is no account with id: %s", id));
        }
        Client client = clientRepo.findById(accountRepo.findById(id)
                .get().getClient().getId()).get();
        return clientMapper.mapToDto(client);
    }

    public ClientDto create(ClientDto clientDto) throws RuntimeException{

        if (clientRepo.existsByEmail(clientDto.getEmail())){
            throw new RuntimeException(String.format(
                    "A client with email %s already exists!", clientDto.getEmail()));
        }

        return clientMapper.mapToDto(
                clientRepo.save(clientMapper.mapToEntity(clientDto)));
    }

    // this service allows to update
    public ClientDto updateById(Long id, ClientDtoUpdate dto)
            throws ClientNotFoundException, ManagerNotFoundException{

        Client client = clientRepo.findById(id)
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
        Client updatedClient = clientRepo.save(client);
        return clientMapper.mapToDto(updatedClient);
    }

    public ClientDto deleteById(Long id) throws ClientNotFoundException{

        Client client = clientRepo.findById(id)
                .orElseThrow(() -> new ClientNotFoundException(
                        String.format("There is no client with id: %s", id)));
        client.setStatus(Status.INACTIVE);
        client.getAccounts()
                .forEach(account -> account.setStatus(Status.INACTIVE));
        Client savedClient = clientRepo.save(client);
        return clientMapper.mapToDto(savedClient);
    }
}
