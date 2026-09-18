package com.JavaSpring.usuario.exception;

public class ResourceNotException extends RuntimeException{

    public ResourceNotException(String mensage){
        super(mensage);
    }

    public ResourceNotException(String mensage, Throwable cause){
        super(mensage,cause);
    }
}
