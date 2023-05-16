package com.example.library.pod.sso;

import com.example.library.object.login.PodTokenRequestDTO;
import com.example.library.object.login.PodTokenResponseDTO;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface PodSso {

    @Headers({"Content-Type: application/x-www-form-urlencoded"})
    @POST("oauth2/token")
    Call<PodTokenResponseDTO> getToken(@Body PodTokenRequestDTO podTokenRequestDTO,
    @Header("Authorization") String authorization);


}
