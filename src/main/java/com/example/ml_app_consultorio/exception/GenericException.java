package com.example.ml_app_consultorio.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenericException extends Exception{
    private Integer code;
    private String message;
    public GenericException(String message, Integer status){
        this.message=message;
        this.code=status;
    }
}
