package com.example.CrudOperation.exception;

import lombok.Builder;

//@ResponseStatus(HttpStatus.BAD_REQUEST)
@Builder
public class StudentNotFoundException  extends  RuntimeException{
    String message;
    public StudentNotFoundException(String msg) {
        super(msg);
        this.message =msg;
    }
}