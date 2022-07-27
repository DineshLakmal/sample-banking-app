package com.banking.sample.exception;

public class ValidationException  extends RuntimeException{

    public ValidationException(String message){
        super(message);
    }
}
