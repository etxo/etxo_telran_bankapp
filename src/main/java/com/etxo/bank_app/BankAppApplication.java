package com.etxo.bank_app;

import com.etxo.bank_app.entity.Client;
import com.etxo.bank_app.reposi.AccountRepository;
import com.etxo.bank_app.reposi.ClientRepository;
import com.etxo.bank_app.reposi.ManagerRepository;
import com.etxo.bank_app.security.repository.UserRepository;
import com.etxo.bank_app.utility.RandomData;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BankAppApplication {

    public static void main(String[] args) {

        SpringApplication.run(BankAppApplication.class, args);
    }

    @Bean
    CommandLineRunner initData(
            ManagerRepository managerRepo,
            ClientRepository clientRepo,
            AccountRepository accountRepo,
            UserRepository userRepo) {
        return args -> {
            RandomData data = new RandomData();
            for (int i = 0; i < 3; i++) {
                managerRepo.save(data.generateRandomManager());
            }

            for (int i = 0; i < 10; i++) {

                Client client = clientRepo.save(data.generateRandomClient(
                        data.managerTrigger(managerRepo.findAll())));
                accountRepo.save(data.generateAccountForClient(client));
            }

            data.generateAndSaveAdminAndManager(userRepo);

            System.out.println("\n\u001B[33m The DATABASE is SUCCESSFULLY INITIALIZED!\u001B[0m");
        };
    }
}

