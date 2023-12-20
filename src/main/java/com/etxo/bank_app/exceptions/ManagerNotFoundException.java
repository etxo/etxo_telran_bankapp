package com.etxo.bank_app.exceptions;

public class ManagerNotFoundException extends RuntimeException{
    public ManagerNotFoundException(String message){
        super(message);
    }
}
