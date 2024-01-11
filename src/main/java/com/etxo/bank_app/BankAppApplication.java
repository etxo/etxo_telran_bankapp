package com.etxo.bank_app;

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
    CommandLineRunner commandLineRunner(
            ManagerRepository managerRepo,
            ClientRepository clientRepo,
            AccountRepository accountRepo,
            UserRepository userRepo) {
        return args -> {
            RandomData randomData = new RandomData(
                    managerRepo, clientRepo, accountRepo, userRepo
            );
            randomData.generateRandomManagers();
            randomData.generateClients();
            randomData.generateAccounts();
            randomData.generateAdminAndManager();
        };
    }
}

