package com.etxo.bank_app.security.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.lang.NonNull;

@Getter @Setter
@NoArgsConstructor
public class AuthRequest {

    @NotNull
    private String username;
    @NonNull
    private String password;
}
