package com.etxo.bank_app.security.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class RefreshTokenRequest {
    private String token;
}
