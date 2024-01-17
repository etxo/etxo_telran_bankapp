package com.etxo.bank_app.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter @Setter
@AllArgsConstructor
public class ErrorResponse{

    private int status;
    private String message;
    private Timestamp time;
}
