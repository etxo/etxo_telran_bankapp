package com.etxo.bank_app.security.service;

import com.etxo.bank_app.security.dto.AuthRequest;
import com.etxo.bank_app.security.dto.AuthResponse;
import com.etxo.bank_app.security.dto.RegisterRequest;
import com.etxo.bank_app.security.entity.Role;
import com.etxo.bank_app.security.entity.User;
import com.etxo.bank_app.security.repository.UserRepository;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

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


    private RegisterRequest request;
    private String encodedPassword;
    private AuthRequest authRequest;
    private AuthResponse expectedResponse;
    @BeforeEach
    public void setUp() {

        Faker faker = new Faker();
        request = new RegisterRequest();
        request.setUsername(faker.name().username());
        request.setEmail(faker.internet().emailAddress());
        request.setPassword(faker.internet().password());

        expectedResponse = new AuthResponse();
        encodedPassword = new BCryptPasswordEncoder()
                .encode(request.getPassword());
        expectedResponse.setToken(new JwtService()
                .generateToken(new User(1L, request.getUsername(),
                        request.getEmail(),
                        request.getPassword(),
                        Role.USER)));
    }

    @Test
    void itShouldRegister() {

        when(repository.existsByEmail(request.getEmail()))
                .thenReturn(false);
        when(jwtService.generateToken(any(User.class)))
                .thenReturn(expectedResponse.getToken());
        when(encoder.encode(anyString())).thenReturn(encodedPassword);

        assertEquals(expectedResponse.getToken(),
                authService.register(request).getToken());
    }

    @Test
    void itShouldAuthenticate() {

        authService.register(request);
        //when(authManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
          //      .thenReturn(SecurityContextHolder.getContext()
            //            .setAuthentication(request.getUsername(), request.getPassword()));

    }
    @Test
    void itShouldNotAuthenticateWithWrongPW() {
    }
}