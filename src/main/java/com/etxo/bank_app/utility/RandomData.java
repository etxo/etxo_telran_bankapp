package com.etxo.bank_app.utility;

import com.etxo.bank_app.entity.Account;
import com.etxo.bank_app.entity.Address;
import com.etxo.bank_app.entity.Client;
import com.etxo.bank_app.entity.Manager;
import com.etxo.bank_app.entity.enums.AccountType;
import com.etxo.bank_app.entity.enums.CountryCode;
import com.etxo.bank_app.entity.enums.Currency;
import com.etxo.bank_app.entity.enums.Status;
import com.etxo.bank_app.exceptions.ClientNotFoundException;
import com.etxo.bank_app.reposi.AccountRepository;
import com.etxo.bank_app.reposi.ClientRepository;
import com.etxo.bank_app.reposi.ManagerRepository;
import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class RandomData {

    private final ManagerRepository managerRepo;
    private final ClientRepository clientRepo;
    private final AccountRepository accountRepo;
    public void generateRandomManagers(ManagerRepository managerRepo) {
        Faker faker = new Faker(new Locale("de"));
        for (int i = 0; i < 3; i++) {
            Manager manager = new Manager();
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            String email = String.format("%s.%s@etxoBank.be", firstName, lastName);
            manager.setFirstName(firstName);
            manager.setLastName(lastName);
            manager.setEmail(email);
            manager.setPhone(String.valueOf(faker.phoneNumber().phoneNumber()));
            manager.setStatus(Status.ACTIVE);
            managerRepo.save(manager);
        }
    }
    public void generateRandomClients(ClientRepository clientRepo,
                                             ManagerRepository managerRepo) {

        Faker faker = new Faker(new Locale("de"));
        for (int i = 0; i < 10; i++) {
            Client client = new Client();
            Address address = new Address();
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
            client.setManager(managerTrigger(managerRepo));
            clientRepo.save(client);
        }
    }
    public Manager managerTrigger(ManagerRepository managerRepo){
        Long randomId = 1L + new Random().nextLong(managerRepo.count());
        System.out.println(randomId);
        Manager entity = managerRepo.findById(randomId).orElse(null);
        return entity;
    }
    public void generateAccounts(AccountRepository accountRepo){

        for(int i = 1; i <= 10; i++){
            generateAccountForClientById((long) i);
        }
    }
    public void generateAccountForClientById(Long id){

        Faker faker = new Faker(new Locale("DE"));
        Account account = new Account();

        if(!clientRepo.findById(id).isPresent())
            throw new ClientNotFoundException("Client not found");

        account.setClient(clientRepo.findById(id).get());
        account.setIban(faker.finance().iban());
        account.setBic(faker.finance().bic());
        account.setAccountType(AccountType.DEBIT);
        account.setStatus(Status.ACTIVE);
        account.setBalance(new BigDecimal(100.0));
        account.setCurrencyCode(Currency.EUR);
        account.setSentTransactions(new ArrayList<>());
        account.setReceivedTransactions(new ArrayList<>());
        accountRepo.save(account);
    }
}
