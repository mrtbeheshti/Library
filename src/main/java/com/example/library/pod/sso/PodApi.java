package com.example.library.pod.sso;

import com.example.library.vo.auth.PodUserReqVo;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface PodApi {
    @Headers("_token_issuer_: 1")
    @GET("srv/core/nzh/getUserProfile")
    Call<PodUserReqVo> getUser(
            @Query("client_id") String clientId,
            @Query("client_secret") String clientSecret,
            @Header("_token_") String token
    );

}
