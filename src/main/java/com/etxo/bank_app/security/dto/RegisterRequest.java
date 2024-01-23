package com.etxo.bank_app.security.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter @Setter
@NoArgsConstructor
public class RegisterRequest {


    @NotEmpty(message = "username may not be empty!")
    @Length(min = 3, max = 25)
    private String username;

    @Email(message = "this is not a valid email!")
    private String email;

    @NotEmpty(message = "password may not be empty!")
    @Length(min = 6, max = 25)
    private String password;
}
