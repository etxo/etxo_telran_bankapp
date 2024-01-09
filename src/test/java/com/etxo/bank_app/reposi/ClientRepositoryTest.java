package com.etxo.bank_app.reposi;

import com.etxo.bank_app.entity.Client;
import com.etxo.bank_app.entity.enums.Status;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ClientRepositoryTest {

    @Autowired
    private ClientRepository repository;

    private Client expectedClient;

    @BeforeEach
    void setUp() {

        Faker faker = new Faker(new Locale("DE"));
        expectedClient = new Client();
        expectedClient.setStatus(Status.ACTIVE);
        expectedClient.setFirstName(faker.name().firstName());
        expectedClient.setLastName(faker.name().lastName());
        expectedClient.setEmail(faker.internet().emailAddress());
    }

    @Test
    void shouldGetClientByEmail() {

        repository.save(expectedClient);
        Client savedClient = repository.getClientByEmail(expectedClient.getEmail())
                        .orElseThrow();

        assertTrue(repository.existsByEmail(expectedClient.getEmail()));
        assertEquals(expectedClient, savedClient);
    }
}