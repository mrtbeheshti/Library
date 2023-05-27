package com.example.library.pod.sso;

import com.example.library.vo.auth.PodTokenResponseVo;
import retrofit2.Call;
import retrofit2.http.*;

public interface PodAccounts {
    @FormUrlEncoded
    @POST("oauth2/token/")
    Call<PodTokenResponseVo> getToken(@Field("grant_type") String grantType,
                                      @Field("code") String code,
                                      @Field("redirect_uri") String redirectUri,
                                      @Header("Authorization") String authorization);
}

