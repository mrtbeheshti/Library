package com.example.library.object.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class PodTokenResponseDTO {

    @SerializedName("access_token")
    @Expose
    private String accessToken;
    @SerializedName("refresh_token")
    @Expose
    private String refreshToken;
    @Expose
    @SerializedName("id_token")
    private String idToken;
    @SerializedName("token_type")
    @Expose
    private String tokenType;
    @SerializedName("expires_in")
    @Expose
    private String expiresIn;
    @Expose
    @SerializedName("scope")
    private String scope;
    @SerializedName("device_uid")
    @Expose
    private String deviceUid;


}
