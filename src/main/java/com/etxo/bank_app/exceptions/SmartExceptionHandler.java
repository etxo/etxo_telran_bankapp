package com.etxo.bank_app.exceptions;

import com.etxo.bank_app.entity.Account;
import com.etxo.bank_app.entity.Manager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.Timestamp;

@ControllerAdvice
public class SmartExceptionHandler
        extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ClientNotFoundException.class,
                        AccountNotFoundException.class,
                        ManagerNotFoundException.class})
    public ResponseEntity<ErrorResponse> handleNotFound(Exception e){

        return new ResponseEntity<>(new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                e.getMessage(),
                new Timestamp(System.currentTimeMillis())),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleEmailExists(
            EmailExistsException e){

        return new ResponseEntity<>(new ErrorResponse(
                HttpStatus.IM_USED.value(),
                e.getMessage(),
                new Timestamp(System.currentTimeMillis())),
                HttpStatus.IM_USED);
    }
}
