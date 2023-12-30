package com.etxo.bank_app.security.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class JwtAuthResponse {

    private String token;
    private String refreshToken;
}
