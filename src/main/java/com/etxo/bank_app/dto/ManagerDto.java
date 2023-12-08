package com.etxo.bank_app.dto;

import com.etxo.bank_app.entity.enums.Status;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Setter
@Getter
@NoArgsConstructor
public class ManagerDto {
    private Long id;
    @NotNull(message = "First name shouldn't be null.")
    @Size(min = 2, max = 25)
    private String firstName;
    @NotNull(message = "Last name shouldn't be null.")
    @Size(min = 2, max = 25)
    private String lastName;
    @NotNull
    private String email;
    private String phone;
    private Status status;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
