package com.example.library.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Setter
public class BaseException extends RuntimeException {
    private HttpStatus status;
    private LocalDateTime time;
    public BaseException(String message, ExceptionsEnum exceptionsEnum){
        super(message);
        this.status = exceptionsEnum.getStatus();
        this.time = LocalDateTime.now();
    }

}
