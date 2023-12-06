package com.etxo.bank_app.dto;

import com.etxo.bank_app.entity.Account;
import com.etxo.bank_app.entity.Address;
import com.etxo.bank_app.entity.Status;
import lombok.*;

import java.sql.Timestamp;
import java.util.List;

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
    private int managerId;
    private List<Account> accounts;
}
