package com.example.library.vo.auth;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PodUserInfoVo {
    @SerializedName("firstName")
    private String firstName;
    @SerializedName("lastName")
    private String lastName;
    @SerializedName("name")
    private String name;
    @SerializedName("gender")
    private String gender;
    @SerializedName("nickName")
    private String nickName;
    @SerializedName("userId")
    private String userId;
    @SerializedName("guest")
    private String guest;
    @SerializedName("username")
    private String username;
    @SerializedName("ssoId")
    private long ssoId;
    @SerializedName("ssoIssuerCode")
    private String ssoIssuerCode;
    @SerializedName("legalInfo")
    private Object legalInfo;
    @SerializedName("readOnlyFields")
    private String readOnlyFields;

}
