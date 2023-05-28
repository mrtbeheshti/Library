package com.example.library.service;

import com.example.library.entity.User;
import com.example.library.enums.ExceptionEnum;
import com.example.library.enums.RoleEnum;
import com.example.library.enums.SubjectEnum;
import com.example.library.exception.BaseException;
import com.example.library.pojo.log.RequestLogPOJO;
import com.example.library.pojo.log.ResponseLogPOJO;
import com.example.library.pojo.log.ThrowableLogPOJO;
import com.example.library.security.SocketPrincipal;
import com.example.library.util.LogUtil;
import com.example.library.vo.auth.PodTokenResponseVo;
import com.example.library.vo.auth.PodUserInfoVo;
import com.example.library.pod.sso.PodAccounts;
import com.example.library.pod.sso.PodApi;
import com.example.library.repository.UserRepository;
import com.example.library.vo.auth.PodUserReqVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Log4j2
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
        } catch (IOException ex) {
            SocketPrincipal socketPrincipal = (SocketPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            ThrowableLogPOJO throwableLog = ThrowableLogPOJO.builder()
                    .message("IOException at *login*")
                    .subject(SubjectEnum.AUTH)
                    .ssoId(String.valueOf(socketPrincipal.getSsoId()))
                    .details(ex)
                    .build();
            LogUtil.error(log,throwableLog);
        }
    }

    @Override
    public void redirect(String code, HttpServletResponse response) {
        PodAccounts podAccounts = ServiceGenerator.createService(PodAccounts.class, ssoAddress + "/");
        String base64Auth = "Basic " + Base64.getEncoder().encodeToString((String.format("%s:%s", clientId, clientSecret)).getBytes());
        PodTokenResponseVo tokenResponseVo;
        try {
            Call<PodTokenResponseVo> call = podAccounts.getToken(
                    grantType,
                    code,
                    redirectUri,
                    base64Auth
            );
            RequestLogPOJO<PodTokenResponseVo> requestLog = RequestLogPOJO.<PodTokenResponseVo>builder()
                    .details(call)
                    .message("request to pod at *redirect*")
                    .build();
            LogUtil.info(log, requestLog);
            Response<PodTokenResponseVo> reqResponse = call.execute();

            tokenResponseVo = reqResponse.body();
            ResponseLogPOJO<PodTokenResponseVo> responseLog = ResponseLogPOJO.<PodTokenResponseVo>builder()
                    .details(reqResponse)
                    .message("response from pod at *redirect*")
                    .build();
            LogUtil.info(log, responseLog);
        } catch (IOException exception) {
            SocketPrincipal socketPrincipal = (SocketPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            ThrowableLogPOJO throwableLog = ThrowableLogPOJO.builder()
                    .message("IOException at *redirect*")
                    .subject(SubjectEnum.AUTH)
                    .ssoId(String.valueOf(socketPrincipal.getSsoId()))
                    .details(exception)
                    .build();
            LogUtil.error(log,throwableLog);
            return;
        }
        if (tokenResponseVo == null) {
            return;
        }
        PodUserInfoVo userInfo = getUserFromPod(tokenResponseVo.getAccessToken()).orElseThrow(() -> new BaseException("aloof", ExceptionEnum.NOT_EXIST));

        if (!userRepository.existsBySsoId(userInfo.getSsoId())) {
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

    public Optional<PodUserInfoVo> getUserFromPod(String accessToken) {
        PodApi podApi = ServiceGenerator.createService(PodApi.class, "https://api.pod.ir/");
        try {
//            return Optional.ofNullable(Objects.requireNonNull(podApi.getUser(clientId, clientSecret, accessToken).execute().body()).getResult());
            Call<PodUserReqVo> call = podApi.getUser(clientId, clientSecret, accessToken);
            RequestLogPOJO<PodUserReqVo> requestLog= RequestLogPOJO.<PodUserReqVo>builder()
                    .message("request to pod at *getUserFromPod*")
                    .details(call)
                    .build();
            LogUtil.info(log,requestLog);
            Response<PodUserReqVo> reqResponse = call.execute();
            PodUserReqVo userReq = reqResponse.body();
            ResponseLogPOJO<PodUserReqVo> responseLog = ResponseLogPOJO.<PodUserReqVo>builder()
                    .details(reqResponse)
                    .message("response from pod at *getUserFromPod*")
                    .build();
            LogUtil.info(log, responseLog);
            return Optional.ofNullable(Objects.requireNonNull(userReq).getResult());
        } catch (IOException exception) {
            SocketPrincipal socketPrincipal = (SocketPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            ThrowableLogPOJO throwableLog = ThrowableLogPOJO.builder()
                    .message("IOException at *getUserFromPod*")
                    .subject(SubjectEnum.AUTH)
                    .ssoId(String.valueOf(socketPrincipal.getSsoId()))
                    .details(exception)
                    .build();
            LogUtil.error(log,throwableLog);
            return Optional.empty();
        }

    }


}
