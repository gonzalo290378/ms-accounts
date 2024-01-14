package com.bench.msaccounts.exceptions;

public class AccountTypeNotFoundException extends RuntimeException{

    public AccountTypeNotFoundException(String message){
        super(message);
    }
}
