package com.example.library.vo.log;

import lombok.*;

@Getter
@Setter
public abstract class LogObjectVo {
    private String message;

    abstract String getLog();

    abstract String getDetailsForLog();
}
