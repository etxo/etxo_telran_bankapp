package com.etxo.bank_app.exceptions;

public class ClientNotFoundException extends RuntimeException{
    public ClientNotFoundException (String message){
        super(message);
    }
}
