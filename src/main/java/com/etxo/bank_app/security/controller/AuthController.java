package com.etxo.bank_app.security.controller;

import com.etxo.bank_app.security.dto.SignUpRequest;
import com.etxo.bank_app.security.entity.User;
import com.etxo.bank_app.security.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService service;

    @PostMapping("/signup")
    public ResponseEntity<User> signUp(@RequestBody SignUpRequest request){
        return ResponseEntity.ok(service.signUp(request));
    }
}
