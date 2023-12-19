package com.etxo.bank_app.dto;

import com.etxo.bank_app.entity.enums.Status;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.sql.Timestamp;

@Setter
@Getter
@NoArgsConstructor
@EqualsAndHashCode
public class ManagerDto {

    private Long id;
    @Size(min = 2, max = 25)
    private String firstName;

    @Size(min = 2, max = 25)
    private String lastName;

    @Email
    private String email;

    @Length(min = 7, max = 15)
    private String phone;

    private Status status;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
