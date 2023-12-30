package com.etxo.bank_app.security.service;

import com.etxo.bank_app.exceptions.ClientNotFoundException;
import com.etxo.bank_app.security.dto.JwtAuthResponse;
import com.etxo.bank_app.security.dto.RefreshTokenRequest;
import com.etxo.bank_app.security.dto.SignInRequest;
import com.etxo.bank_app.security.dto.SignUpRequest;
import com.etxo.bank_app.security.entity.Role;
import com.etxo.bank_app.security.entity.User;
import com.etxo.bank_app.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository repository;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authManager;
    private final JwtService jwtService;

    public User signUp(SignUpRequest request){

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setRole(Role.USER);
        user.setPassword(encoder.encode(request.getPassword()));
        return repository.save(user);
    }

    public JwtAuthResponse signIn(SignInRequest request){
        authManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),
                request.getPassword()));

        var user = repository.findByUsername(request.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("No user with this name!"));

        var accessToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);

        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
        jwtAuthResponse.setToken(accessToken);
        jwtAuthResponse.setRefreshToken(refreshToken);

        return jwtAuthResponse;
    }

    public JwtAuthResponse refreshToken(RefreshTokenRequest request){
        String username = jwtService.extractUserName(request.getToken());
        User user = repository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("no such user!"));
        if(jwtService.isTokenValid(request.getToken(), user)){
            var jwt = jwtService.generateToken(user);
            JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
            jwtAuthResponse.setToken(jwt);
            jwtAuthResponse.setRefreshToken(request.getToken());

            return jwtAuthResponse;
        }
        return null;
    }
}
