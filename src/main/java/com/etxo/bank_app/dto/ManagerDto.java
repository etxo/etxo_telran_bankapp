package com.etxo.bank_app.dto;

import com.etxo.bank_app.entity.enums.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Setter
@Getter
@NoArgsConstructor
public class ManagerDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Status status;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
