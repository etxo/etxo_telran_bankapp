package com.etxo.bank_app.dto;

import com.etxo.bank_app.entity.Account;
import com.etxo.bank_app.entity.Address;
import com.etxo.bank_app.entity.Manager;
import com.etxo.bank_app.entity.enums.Status;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.sql.Timestamp;
import java.util.Set;

@Setter @Getter
@NoArgsConstructor
public class ClientDto {

    private Long id;
    private Status status;
    private String firstName;
    private String lastName;
    private String email;
    @JsonProperty("address")
    private AddressDto address;
    private String phone;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    @JsonProperty("manager")
    private ManagerDto manager;
    private Set<AccountDto> accounts;
}
