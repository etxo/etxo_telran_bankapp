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
    private String taxCode;
    private String firstName;
    private String lastName;
    private String email;
    private Address address;
    private String phone;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    @JsonProperty("manager")
    private Manager manager;
    private Set<Account> accounts;
}
