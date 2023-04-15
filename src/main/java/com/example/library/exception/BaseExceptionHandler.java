package com.example.library.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BaseExceptionHandler {
    @ExceptionHandler(value = BaseException.class)
    public ResponseEntity<ExceptionResponse> baseExceptionHandler(BaseException ex){
         ExceptionResponse exceptionResponse = new ExceptionResponse(ex.getMessage(), ex.getStatus().getReasonPhrase(), ex.getTime());
        return new ResponseEntity<>(exceptionResponse,ex.getStatus());
    }
}
