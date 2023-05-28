package com.example.library.pojo.log;

import com.example.library.enums.SubjectEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@Setter
public class ThrowableLogPOJO extends LogObjectPOJO {
    @Builder.Default
    private SubjectEnum subject = SubjectEnum.EXCEPTION;
    private Throwable details;
    private String ssoId;

    @Override
    public String getLog() {
        return String.format("ssoId: %s, message: %s, subject: %s, throws: %s",
                this.getSsoId()!= null?
                        this.getSsoId().equals("S") ?
                                "This is system"
                                : this.getSsoId()
                        : "Not logged in",
                this.getMessage(),
                this.getSubject().name(),
                this.getDetailsForLog()
        );
    }

    @Override
    String getDetailsForLog() {
        return details.getMessage();
    }
}
