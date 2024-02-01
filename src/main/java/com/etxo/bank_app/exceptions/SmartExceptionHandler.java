package com.etxo.bank_app.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
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

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleNotOwnAccount(
            NotOwnAccountException e){

        return new ResponseEntity<>(new ErrorResponse(
                HttpStatus.FORBIDDEN.value(),
                e.getMessage(),
                new Timestamp(System.currentTimeMillis())),
                HttpStatus.FORBIDDEN);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
                                        MethodArgumentNotValidException ex,
                                        HttpHeaders headers,
                                        HttpStatusCode status,
                                        WebRequest request) {

        return new ResponseEntity<>(new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                new Timestamp(System.currentTimeMillis())),
                HttpStatus.BAD_REQUEST);
    }
}
