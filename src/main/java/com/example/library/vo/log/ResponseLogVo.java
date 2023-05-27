package com.example.library.vo.log;

import com.example.library.enums.SubjectEnum;
import retrofit2.Call;

public class ResponseLogVo<S> extends LogObjectVo{
    private String message;
    private final SubjectEnum subject=SubjectEnum.RESPONSE;
    private Call<S> details;

    @Override
    public String getLog() {
        return null;
    }


    @Override
    protected String getDetailsForLog() {
        return null;
    }
}
