package com.example.library.object.login;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class PodTokenRequestDTO {
    @SerializedName("grant_type")
    @Expose
    private String grantType;
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("redirect_uri")
    @Expose
    private String redirectUri;
}
