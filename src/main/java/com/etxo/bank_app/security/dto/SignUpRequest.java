package com.etxo.bank_app.security.dto;

import lombok.Data;

@Data
public class SignUpRequest {

    private String username;
    private String email;
    private String password;
}
