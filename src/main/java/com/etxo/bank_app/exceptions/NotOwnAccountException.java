package com.etxo.bank_app.exceptions;

public class NotOwnAccountException extends RuntimeException{
    public NotOwnAccountException(String message){
        super(message);
    }
}
