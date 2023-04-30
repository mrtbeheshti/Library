package com.example.library.exception;

import com.example.library.enums.ExceptionEnum;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Setter
public class BaseException extends RuntimeException {
    private HttpStatus status;
    private LocalDateTime time;
    public BaseException(String message, ExceptionEnum exceptionEnum){
        super(message);
        this.status = exceptionEnum.getStatus();
        this.time = LocalDateTime.now();
    }

}
