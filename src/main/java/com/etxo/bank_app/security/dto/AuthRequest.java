package com.etxo.bank_app.security.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.NonNull;

@Getter @Setter
@NoArgsConstructor
public class AuthRequest {

    @NonNull
    private String username;
    @NonNull
    private String password;
}
