package com.etxo.bank_app.security.service;

import com.etxo.bank_app.security.dto.AuthRequest;
import com.etxo.bank_app.security.dto.AuthResponse;
import com.etxo.bank_app.security.dto.RegisterRequest;
import com.etxo.bank_app.security.entity.Role;
import com.etxo.bank_app.security.entity.User;
import com.etxo.bank_app.security.repository.UserRepository;
import com.github.javafaker.Faker;
import org.hibernate.mapping.Any;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock
    private UserRepository repository;
    @Mock
    private AuthenticationManager authManager;
    @Mock
    private JwtService jwtService;
    @Mock
    private PasswordEncoder encoder;
    @InjectMocks
    private AuthService authService;


    private RegisterRequest registerRequest;
    private String encodedPassword;
    private AuthRequest authRequest;
    private AuthResponse expectedResponse;
    private
    @BeforeEach
    void setUp() {

        Faker faker = new Faker();
        registerRequest = new RegisterRequest();
        registerRequest.setUsername(faker.name().username());
        registerRequest.setEmail(faker.internet().emailAddress());
        registerRequest.setPassword(faker.internet().password());

        expectedResponse = new AuthResponse();
        encodedPassword = new BCryptPasswordEncoder()
                .encode(registerRequest.getPassword());
        expectedResponse.setToken(new JwtService()
                .generateToken(new User(1L, registerRequest.getUsername(),
                        registerRequest.getEmail(),
                        registerRequest.getPassword(),
                        Role.USER)));
    }

    @Test
    void itShouldRegister() {

        when(repository.existsByEmail(registerRequest.getEmail()))
                .thenReturn(false);
        when(jwtService.generateToken(any(User.class)))
                .thenReturn(expectedResponse.getToken());
        when(encoder.encode(anyString())).thenReturn(encodedPassword);

        assertEquals(expectedResponse.getToken(),
                authService.register(registerRequest).getToken());
    }

    @Test
    void itShouldAuthenticate() {
    }
    @Test
    void itShouldNotAuthenticateWithWrongPW() {
    }
}