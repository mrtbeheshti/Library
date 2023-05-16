package com.example.library.service;

import com.example.library.object.login.PodTokenRequestDTO;
import com.example.library.object.login.PodTokenResponseDTO;
import com.example.library.pod.sso.PodSso;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.servlet.http.HttpServletResponse;
import java.util.Base64;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService{

    @Value("${pod.sso.sso.address}")
    private String ssoAddress;
    @Value("${pod.sso.client.id}")
    private String clientId;
    @Value("${pod.sso.login.response.type}")
    private String responseType;
    @Value("${pod.sso.login.redirect.uri}")
    private String redirectUri;
    @Value("${pod.sso.login.scope}")
    private String scope;
    @Value("${pod.sso.client.secret}")
    private String clientSecret;
    @Value("${pod.sso.login.grant.type}")
    private String grantType;
    private final String baseAuth = "Basic " + Base64.getEncoder().encodeToString((clientId+":"+clientSecret).getBytes());
//    private final String baseAuth ="Basic MTI0NzU0bzEzMDk0NGQwYmM1ZDIwMjc3ZGFjODkzOTowNzY4OTk2ZQ==";
    @Override
    public void login(HttpServletResponse response) {
        try {
            response.sendRedirect(getLoginAddress());
        }
        catch (Exception ex){
            ex.printStackTrace();
        }


    }

    @Override
    public void redirect(String code, HttpServletResponse response){
        PodTokenRequestDTO podTokenRequestDTO = PodTokenRequestDTO.builder()
                .grantType(grantType)
                .code(code)
                .redirectUri(redirectUri)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(ssoAddress)
                .build();
        PodSso podSso = retrofit.create(PodSso.class);
        try {
            Call<PodTokenResponseDTO> tokenResponseDTO = podSso.getToken(podTokenRequestDTO,"Basic " + Base64.getEncoder().encodeToString((clientId+":"+clientSecret).getBytes()));
            System.out.println(tokenResponseDTO.request());
            Response<PodTokenResponseDTO> response1 = tokenResponseDTO.execute();
//            PodTokenResponseDTO tokenResponseDTO1 = response1.body();
            System.out.println(response1);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }

    }

    public String getLoginAddress() {
        return String.format("%s/oauth2/authorize/?client_id=%s&response_type=%s&redirect_uri=%s&scope=%s",
                ssoAddress,
                clientId,
                responseType,
                redirectUri,
                scope
        );

    }


}
