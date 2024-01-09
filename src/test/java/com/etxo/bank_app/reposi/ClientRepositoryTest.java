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

    private Client expClient;

    @BeforeEach
    void setUp() {

        Faker faker = new Faker(new Locale("DE"));
        expClient = new Client();
        expClient.setStatus(Status.ACTIVE);
        expClient.setFirstName(faker.name().firstName());
        expClient.setLastName(faker.name().lastName());
        expClient.setEmail(faker.internet().emailAddress());
    }

    @Test
    void shouldGetClientByEmail() {

        repository.save(expClient);
        Client savedClient = repository.getClientByEmail(expClient.getEmail())
                        .orElseThrow();

        assertTrue(repository.existsByEmail(expClient.getEmail()));
        assertEquals(expClient, savedClient);
    }
}