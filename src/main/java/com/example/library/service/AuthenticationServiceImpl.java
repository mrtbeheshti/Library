package com.example.library.service;

import com.example.library.entity.User;
import com.example.library.enums.RoleEnum;
import com.example.library.util.LogUtil;
import com.example.library.vo.auth.PodTokenResponseVo;
import com.example.library.vo.auth.PodUserInfoVo;
import com.example.library.pod.sso.PodAccounts;
import com.example.library.pod.sso.PodApi;
import com.example.library.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

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
    private final UserRepository userRepository;

    @Override
    public void login(HttpServletResponse response) {
        try {
            response.sendRedirect(getLoginAddress());
        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }

    @Override
    public void redirect(String code, HttpServletResponse response) {
//        log.debug("My Debug Log");
//        log.info("My Info Log");
//        log.warn("My Warn Log");
//        log.error("My error log");
//        log.fatal("My fatal log");
        PodAccounts podAccounts = ServiceGenerator.createService(PodAccounts.class,ssoAddress+"/");
        String base64Auth = "Basic " + Base64.getEncoder().encodeToString((String.format("%s:%s",clientId,clientSecret)).getBytes());
        PodTokenResponseVo tokenResponseDTO;
        try {
            tokenResponseDTO = podAccounts.getToken(
                    grantType,
                    code,
                    redirectUri,
                    base64Auth
            )
                    .execute().body();

        } catch (IOException exception) {
            exception.printStackTrace();
            return;
        }
        if (tokenResponseDTO == null) {
//            log.warn("code {} is invalid.", code);
            return;
        }
        PodUserInfoVo userInfo = getUserFromPod(tokenResponseDTO.getAccessToken()).get();
//        log.info(String.format("Refresh token: %s", tokenResponseDTO.getRefreshToken()));
//        log.info(String.format("Access token: %s", tokenResponseDTO.getAccessToken()));
//        log.info(String.format("Expires in: %s", tokenResponseDTO.getExpiresIn()));

        if(!userRepository.existsBySsoId(userInfo.getSsoId())){
            User newUser = User.from(userInfo, Set.of(RoleEnum.ROLE_USER));
            userRepository.save(newUser);
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

    public Optional<PodUserInfoVo> getUserFromPod(String accessToken){
        PodApi podApi = ServiceGenerator.createService(PodApi.class,"https://api.pod.ir/");
        try {
            return Optional.ofNullable(podApi.getUser(clientId, clientSecret, accessToken).execute().body().getResult());
        } catch (IOException exception) {
            exception.printStackTrace();
            return null;
        }
    }


}
