package com.etxo.bank_app.dto;

import com.etxo.bank_app.entity.enums.Status;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;


@Getter @Setter
@NoArgsConstructor
public class ClientDtoUpdate {

    private Status status;

    @Length(min = 2, max = 22)
    private String firstName;

    @Length(min = 2, max = 32)
    private String lastName;

    @Email
    private String email;

    @JsonProperty("address")
    private AddressDto address;

    @Length(min = 7, max = 15)
    private String phone;

    private Long managerId;
}
