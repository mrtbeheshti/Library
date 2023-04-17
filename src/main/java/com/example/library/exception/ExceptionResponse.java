package com.example.library.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Setter
@Getter
public class ExceptionResponse {
    private String message;
    private String status;
    private LocalDateTime time;

    public ExceptionResponse(String message ,String status,LocalDateTime time){
        this.setMessage(message);
        this.setStatus(status);
        this.setTime(time);
    }
}
