package com.example.ml_app_consultorio.exception;

import org.springframework.http.HttpStatus;

public class EntityNotFoundException extends GenericException{
    public EntityNotFoundException(String msg){
        super(msg, HttpStatus.BAD_REQUEST.value());
    }
}
