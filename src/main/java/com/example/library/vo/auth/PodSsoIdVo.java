package com.example.library.vo.auth;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PodSsoIdVo {
    @SerializedName("active")
//    @Expose
    private boolean active;
    @SerializedName("client_id")
//    @Expose
    private String clientId;
    @SerializedName("scope")
//    @Expose
    private String scope;
    @SerializedName("sub")
//    @Expose
    private long ssoId;
}
