package com.example.library.vo.auth;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Builder
@ToString
public class PodTokenResponseVo {

    @SerializedName("access_token")
    @ToString.Exclude
    private String accessToken;
    @SerializedName("refresh_token")
    @ToString.Exclude
    private String refreshToken;
    @SerializedName("id_token")
    @ToString.Exclude
    private String idToken;
    @SerializedName("token_type")
//    @ToString.Exclude
    private String tokenType;
    @SerializedName("expires_in")
//    @ToString.Exclude
    private String expiresIn;
    @SerializedName("scope")
    @ToString.Exclude
    private String scope;
    @SerializedName("device_uid")
    @ToString.Exclude
    private String deviceUid;
}
