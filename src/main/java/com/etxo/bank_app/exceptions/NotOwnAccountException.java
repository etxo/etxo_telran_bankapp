package com.etxo.bank_app.exceptions;

public class NotAccountOwnerException extends RuntimeException{
    public NotAccountOwnerException(String message){
        super(message);
    }
}
