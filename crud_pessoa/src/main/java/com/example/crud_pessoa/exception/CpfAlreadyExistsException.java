package com.example.crud_pessoa.exception;

public class CpfAlreadyExistsException extends RuntimeException {
    public CpfAlreadyExistsException(String message){
        super(message);
    }
}
