package com.etxo.bank_app.security.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class AuthRequest {

    @NotEmpty(message = "enter your username please:")
    private String username;
    @NotEmpty(message = "enter your password please:")
    private String password;
}
