package com.etxo.bank_app.security.service;

import com.etxo.bank_app.security.dto.SignUpRequest;
import com.etxo.bank_app.security.entity.Role;
import com.etxo.bank_app.security.entity.User;
import com.etxo.bank_app.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository repository;
    private final PasswordEncoder encoder;

    public User signUp(SignUpRequest request){

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setRole(Role.USER);
        user.setPassword(encoder.encode(request.getPassword()));
        return repository.save(user);
    }
}
