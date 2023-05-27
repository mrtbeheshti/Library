package com.example.library.vo.log;

import com.example.library.enums.SubjectEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import okhttp3.Request;
import retrofit2.Call;

import static java.lang.String.format;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class RequestVoLogVo<S> extends LogObjectVo{
    private final SubjectEnum subject = SubjectEnum.REQUEST;
    private Call<S> details;

    @Override
    public String getLog() {
        return format("\nmessage: %s\nsubject: %s\nrequest: %s",
                this.getMessage(),
                this.getSubject().name(),
                this.getDetailsForLog());
    }

    @Override
    protected String getDetailsForLog() {
        Request request = this.getDetails().request();
        return format("{URL: %s,%nHeaders: %s,%nBody: %s}",
                request.url(),
                request.headers(),
                request.body() != null ? request.body().toString() : null);
    }
}
