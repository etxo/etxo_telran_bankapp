package com.etxo.bank_app.security.repository;

import com.etxo.bank_app.security.entity.Role;
import com.etxo.bank_app.security.entity.User;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository repository;

    private User expectedUser;
    @BeforeEach
    void setUp() {

        Faker faker = new Faker();
        expectedUser = new User();
        expectedUser.setUsername(faker.name().username());
        expectedUser.setEmail(faker.internet().emailAddress());
        expectedUser.setPassword(faker.internet().password(6,25));
        expectedUser.setRole(Role.USER);
    }

    @Test
    void itShouldFindUserByUsername() {

        repository.save(expectedUser);
        assertNotNull(repository.findByUsername(expectedUser.getUsername()));
        assertEquals(expectedUser, repository.findByUsername(expectedUser.getUsername()).get());
    }

    @Test
    void findByRole() {

        repository.save(expectedUser);
        assertEquals(expectedUser, repository.findByRole(Role.USER).get());
    }

    @Test
    void itShouldCheckIfUserExistsByEmail() {

        repository.save(expectedUser);
        assertTrue(repository.existsByEmail(expectedUser.getEmail()));
    }
}