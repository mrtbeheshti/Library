package com.example.library.exception;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
@Getter
@RequiredArgsConstructor
public enum ExceptionsEnum {
    ALREADY_EXIST(HttpStatus.CONFLICT),
    NOT_EXIST(HttpStatus.NOT_FOUND),
    IS_RESERVED(HttpStatus.FORBIDDEN),
    MAXIMUM_RESERVES_REACHED(HttpStatus.FORBIDDEN);

    private final HttpStatus status;

}
