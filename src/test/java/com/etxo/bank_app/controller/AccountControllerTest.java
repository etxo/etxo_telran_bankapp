package com.etxo.bank_app.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AccountControllerTest {


    private TestRestTemplate restTemplate;
    @BeforeEach
    void setUp() {
        restTemplate = new TestRestTemplate();
    }

    @AfterEach
    void tearDown() {
    }


}