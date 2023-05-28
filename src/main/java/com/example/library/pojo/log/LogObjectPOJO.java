package com.example.library.pojo.log;

import lombok.*;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class LogObjectPOJO {
    private String message;

    abstract public String getLog();

    abstract String getDetailsForLog();
}
