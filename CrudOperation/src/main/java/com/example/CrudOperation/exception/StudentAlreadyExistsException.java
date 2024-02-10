package com.example.CrudOperation.exception;

import lombok.Builder;

//@ResponseStatus(HttpStatus.FOUND)
@Builder
public class StudentAlreadyExistsException extends RuntimeException{
    String message;
    public StudentAlreadyExistsException(String msg) {
        super(msg);
        this.message = msg;
    }
}
