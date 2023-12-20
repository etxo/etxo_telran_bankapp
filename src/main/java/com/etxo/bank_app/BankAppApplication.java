package com.etxo.bank_app;

import com.etxo.bank_app.dto.AccountDto;
import com.etxo.bank_app.dto.AddressDto;
import com.etxo.bank_app.dto.ClientDto;
import com.etxo.bank_app.dto.ManagerDto;
import com.etxo.bank_app.entity.Account;
import com.etxo.bank_app.entity.Manager;
import com.etxo.bank_app.entity.enums.AccountType;
import com.etxo.bank_app.entity.enums.CountryCode;
import com.etxo.bank_app.entity.enums.Currency;
import com.etxo.bank_app.entity.enums.Status;
import com.etxo.bank_app.exceptions.ClientNotFoundException;
import com.etxo.bank_app.service.AccountService;
import com.etxo.bank_app.service.AddressService;
import com.etxo.bank_app.service.ClientService;
import com.etxo.bank_app.service.ManagerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.github.javafaker.Faker;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Locale;

@SpringBootApplication
public class BankAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankAppApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(
            ClientService clientService,
            ManagerService managerService,
            AccountService accountService) {
        return args -> {
            generateRandomManagers(managerService);
            generateRandomClients(clientService, managerService);
            generateAccounts(accountService);
        };
    }

    private void generateRandomManagers(ManagerService service) {
        Faker faker = new Faker(new Locale("de"));
        for (int i = 0; i < 3; i++) {
            ManagerDto manager = new ManagerDto();
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            String email = String.format("%s.%s@etxoBank.be", firstName, lastName);
            manager.setFirstName(firstName);
            manager.setLastName(lastName);
            manager.setEmail(email);
            manager.setPhone(String.valueOf(faker.phoneNumber().phoneNumber()));
            manager.setStatus(Status.ACTIVE);
            service.create(manager);
        }
    }

    private void generateRandomClients(ClientService clientService,
                                       ManagerService managerService) {

        Faker faker = new Faker(new Locale("de"));
        for (int i = 0; i < 10; i++) {
            ClientDto client = new ClientDto();
            AddressDto address = new AddressDto();
            address.setPostalCode(faker.address().zipCode());
            address.setCity(faker.address().city());
            address.setStreet(faker.address().streetName());
            address.setHouseNr(faker.address().buildingNumber());
            address.setCountryCode(CountryCode.DE);
            client.setAddress(address);
            //addressService.save(address);

            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            String email = faker.internet().emailAddress();

            client.setFirstName(firstName);
            client.setLastName(lastName);
            client.setEmail(email);
            client.setPhone(faker.phoneNumber().phoneNumber().toString());
            client.setStatus(Status.ACTIVE);
            client.setManager(managerService.managerTrigger());
            clientService.create(client);
        }
    }
    public void generateAccounts(AccountService service){

        for(int i = 1; i <= 10; i++){
            service.generateAccountForClientById((long) i);
        }
    }
}

