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
import com.etxo.bank_app.exceptions.ManagerNotFoundException;
import com.etxo.bank_app.reposi.ClientRepository;
import com.etxo.bank_app.reposi.ManagerRepository;
import com.etxo.bank_app.security.entity.Role;
import com.etxo.bank_app.security.entity.User;
import com.etxo.bank_app.security.repository.UserRepository;
import com.github.javafaker.Faker;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;

@Component
@NoArgsConstructor
public class RandomData {
    public Manager generateRandomManager() {
        Faker faker = new Faker(new Locale("de"));
            Manager manager = new Manager();
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            String email = String.format("%s.%s@etxoBank.be", firstName, lastName);
            manager.setFirstName(firstName);
            manager.setLastName(lastName);
            manager.setEmail(email);
            manager.setPhone(String.valueOf(faker.phoneNumber().phoneNumber()));
            manager.setStatus(Status.ACTIVE);

            return manager;
    }
    public Client generateRandomClient(Manager manager) {

        Faker faker = new Faker(new Locale("de"));
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
            String email = faker.internet().emailAddress(firstName.concat(lastName));

            client.setFirstName(firstName);
            client.setLastName(lastName);
            client.setEmail(email);
            client.setPhone(faker.phoneNumber().phoneNumber());
            client.setStatus(Status.ACTIVE);
            client.setManager(manager);

            return client;
    }

    public Manager managerTrigger(List<Manager> managers)
                                throws ManagerNotFoundException {
        Long randomId = 1L + new Random().nextLong(managers.size());
        System.out.println(randomId);
        return managers.stream()
                .filter(manager -> manager.getId().equals(randomId))
                .findFirst()
                .orElseThrow(() -> new ManagerNotFoundException("No manager found!"));
    }

    public Account generateAccountForClient(Client client){

        Faker faker = new Faker(new Locale("DE"));
        Account account = new Account();

        account.setClient(client);
        account.setIban(faker.finance().iban());
        account.setBic(faker.finance().bic());
        account.setAccountType(AccountType.DEBIT);
        account.setStatus(Status.ACTIVE);
        account.setBalance(new BigDecimal(100));
        account.setCurrencyCode(Currency.EUR);
        account.setSentTransactions(new ArrayList<>());
        account.setReceivedTransactions(new ArrayList<>());
        return account;
    }

    public void generateAdminAndManager(UserRepository repo){

        if(repo.findByRole(Role.ADMIN).isPresent()) return;
        User admin = new User();
        admin.setUsername("admin");
        admin.setEmail("admin@gmx.de");
        admin.setRole(Role.ADMIN);
        String pw = new BCryptPasswordEncoder().encode("prosto");
        admin.setPassword(pw);
        repo.save(admin);

        if(repo.findByRole(Role.MANAGER).isPresent()) return;
        User manager = new User();
        manager.setUsername("manager");
        manager.setEmail("manager@gmx.de");
        manager.setRole(Role.MANAGER);
        manager.setPassword(pw);
        repo.save(manager);
    }
}
