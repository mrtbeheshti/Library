package com.example.library.vo.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PodUserReqVo {
    private boolean hasError;
    private String messageId;
    private PodUserInfoVo result;
}
