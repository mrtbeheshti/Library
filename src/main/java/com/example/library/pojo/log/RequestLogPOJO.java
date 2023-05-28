package com.example.library.pojo.log;

import com.example.library.enums.SubjectEnum;
import com.example.library.util.LogUtil;
import lombok.*;
import lombok.experimental.SuperBuilder;
import lombok.extern.log4j.Log4j2;
import okhttp3.Request;
import okhttp3.RequestBody;
import okio.Buffer;
import retrofit2.Call;

import java.io.IOException;
import java.util.Objects;

import static java.lang.String.format;

@Log4j2
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class RequestLogPOJO<T> extends LogObjectPOJO {
    private final SubjectEnum subject = SubjectEnum.REQUEST;
    private Call<T> details;
    private String ssoId;

    @Override
    public String getLog() {
        return format("user:%s,message: %s,subject: %s,request: %s",
                this.getSsoId()!= null?
                        this.getSsoId().equals("S") ?
                                "This is system"
                                : this.getSsoId()
                        : "Not logged in",
                this.getMessage(),
                this.getSubject().name(),
                this.getDetailsForLog());
    }

    @Override
    protected String getDetailsForLog() {
        Request request = this.getDetails().request();
        try {
            final Buffer buffer = new Buffer();
            final RequestBody body = request.body();
            Objects.requireNonNull(body).writeTo(buffer);

            return format("{URL: %s,Headers: %s,Body: %s}",
                    request.url(),
                    request.headers(),
                    request.body() != null ? buffer.readUtf8() : null);
        }
        catch (IOException e){
            ThrowableLogPOJO throwableLogPOJO = ThrowableLogPOJO.builder()
                    .message("IOException at getDetailsForLog")
                    .details(e)
                    .ssoId("S")
                    .build();
            LogUtil.error(log,throwableLogPOJO);
            e.printStackTrace();
        }
        catch (NullPointerException e){
            ThrowableLogPOJO throwableLogPOJO = ThrowableLogPOJO.builder()
                    .message("NullPointerException at getDetailsForLog")
                    .details(e)
                    .ssoId("S")
                    .build();
            LogUtil.error(log,throwableLogPOJO);
            return format("{URL: %s, Headers: %s}",
                    request.url(),
                    request.headers());
        }
        return null;
    }
}
