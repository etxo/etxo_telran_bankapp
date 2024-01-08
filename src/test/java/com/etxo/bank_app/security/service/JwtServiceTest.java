package com.etxo.bank_app.security.service;

import com.etxo.bank_app.security.entity.Role;
import com.etxo.bank_app.security.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JwtServiceTest {

    private JwtService jwtService;
    private User user;
    private String token;
    private final String expectedEmail = "buba@mail.by";
    private final String expectedUsername = "buba";

    @BeforeEach
    void setUp() {

        jwtService = new JwtService();
        user = new User();
        user.setId(1L);
        user.setUsername(expectedUsername);
        user.setEmail(expectedEmail);
        user.setPassword("prosto");
        user.setRole(Role.USER);

        token = jwtService.generateToken(user);
    }

    @Test
    void shouldExtractUsername() {

        assertEquals(expectedUsername, jwtService.extractUsername(token));
    }

    @Test
    void shouldExtractEmail() {

        assertEquals(expectedEmail, jwtService.extractEmail(token));
    }

    @Test
    void shouldCheckIfTokenIsValid() {

        assertTrue(jwtService.isTokenValid(token, user));
    }
}