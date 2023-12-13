package com.etxo.bank_app.dto;

import com.etxo.bank_app.entity.Account;
import com.etxo.bank_app.entity.Address;
import com.etxo.bank_app.entity.Manager;
import com.etxo.bank_app.entity.enums.Status;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.sql.Timestamp;
import java.util.Set;

@Setter @Getter
@NoArgsConstructor
public class ClientDto {

    private Long id;
    @NotNull
    private Status status;

    @NotNull@Length(min = 2, max = 22)
    private String firstName;

    @NotNull@Length(min = 2, max = 32)
    private String lastName;

    @NotNull(message = "You need an email to open an account")
    @Email
    private String email;

    @JsonProperty("address")
    private AddressDto address;

    @Length(min = 7, max = 15)
    private String phone;

    private Timestamp createdAt;
    private Timestamp updatedAt;

    @JsonProperty("manager")
    private ManagerDto manager;

    private Set<AccountDto> accounts;
}
