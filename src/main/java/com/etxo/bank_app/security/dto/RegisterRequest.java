package com.etxo.bank_app.security.dto;

import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.lang.NonNull;

@Getter @Setter
@NoArgsConstructor
public class RegisterRequest {


    @NonNull @Length(min = 3)
    private String username;

    @Email
    private String email;

    @NonNull @Length(min = 6)
    private String password;
}
