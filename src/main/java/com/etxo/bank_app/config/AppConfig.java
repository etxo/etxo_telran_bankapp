package com.etxo.bank_app.config;

import com.etxo.bank_app.reposi.AccountRepository;
import com.etxo.bank_app.reposi.ClientRepository;
import com.etxo.bank_app.reposi.ManagerRepository;
import com.etxo.bank_app.security.repository.UserRepository;
import com.etxo.bank_app.utility.RandomData;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
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
