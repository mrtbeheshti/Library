package com.example.library.pojo.log;

import com.example.library.enums.SubjectEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import retrofit2.Response;

@SuperBuilder
@Setter
@Getter
public class ResponseLogPOJO<S> extends LogObjectPOJO {
    private final SubjectEnum subject=SubjectEnum.RESPONSE;
    private Response<S> details;
    private String ssoId;


    @Override
    public String getLog() {
        return String.format("User: %s, Message: %s, Subject: %s, details: %s",
                this.getSsoId()!= null?
                        this.getSsoId().equals("S") ?
                                "This is system"
                                : this.getSsoId()
                        : "Not logged in",
                this.getMessage(),
                this.getSubject(),
                this.getDetailsForLog()
                );
    }


    @Override
    protected String getDetailsForLog() {
        return String.format("{Body: %s}",
                this.details.body()
                );

    }
}
