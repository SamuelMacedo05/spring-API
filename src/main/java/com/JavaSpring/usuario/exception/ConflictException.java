package com.JavaSpring.usuario.exception;

public class ConflictException  extends RuntimeException{

    public ConflictException(String mensage){
        super(mensage);
    }
    public ConflictException(String mensage, Throwable cause){
        super(mensage,cause);
    }
}
