package com.etxo.bank_app.dto;

import com.etxo.bank_app.entity.Manager;

import java.sql.Timestamp;

public class ManagerDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private ManagerStatus status;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public ManagerDto fromEntity(Manager manager){
        return null;
    }

    public Manager fromDto(ManagerDto dto){
        return null;
    }
}
