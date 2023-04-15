package com.example.library.exception;


import org.springframework.http.HttpStatus;

public enum ExceptionsEnum {
    ALREADY_EXIST(HttpStatus.CONFLICT),
    NOT_EXIST(HttpStatus.NOT_FOUND),
    IS_RESERVED(HttpStatus.FORBIDDEN),
    MAXIMUM_RESERVES_REACHED(HttpStatus.FORBIDDEN);

    private final HttpStatus status;

    ExceptionsEnum(HttpStatus status) {
        this.status = status;
    }
    public HttpStatus get(){
        return status;
    }
}
